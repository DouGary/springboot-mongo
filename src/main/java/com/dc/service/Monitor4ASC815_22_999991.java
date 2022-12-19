package com.dc.service;



import com.dc.dal.*;
import com.dc.model.*;
import com.dc.utils.Helper;
import com.dc.utils.Helper2;
import com.dc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.*;



import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Monitor4ASC815_22_999991 {
	@Autowired
	private StockCodeRepository stockCodeRepository;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private ScpoolRepository scpoolRepository;



	@Autowired
	private BollRepository stbollRepository;

	@Autowired
	private StkdjRepository stkdjRepository;

	@Autowired
	private StmacdRepository stmacdRepository;

	@Autowired
	private StockbasicsRepository stockbasicsRepository;

	@Autowired
	private ProfitabilityRepository profitabilityRepository;

	@Autowired
	private HistorytradeInfoRepository historytradeInfoRepository;

	@Autowired
	private Helper helper;
	
	Logger logger = LogManager.getLogger(Monitor4ASC815_22_999991.class);

	public List<StockCode> getFinal(String endDateStr) throws Exception
	{
		
		List<StockCode> Results = new ArrayList();

		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");

		String startDate100DaysBeforeStr = helper.getStartDateStr15daysBefore(endDateStr,-21); // 要当天
        List<String> monitors = new ArrayList<>();
        // 88884，888884，8828，881，8818，8878，88781
//		monitors.add("888846");
//		monitors.add("8888461");
//		monitors.add("8888462");
        monitors.add("88884");
//		monitors.add("888884");
		monitors.add("8828");
//		monitors.add("88");
		monitors.add("881");
		monitors.add("8818");
//		monitors.add("8838");
		monitors.add("88381");
//		monitors.add("889");
		monitors.add("8898");
		monitors.add("8868");
		monitors.add("8878");
		monitors.add("88781");
		List<Scpool> scLst = scpoolRepository.findAllScpoolGteEndDateStrAndSellAllOut(startDate100DaysBeforeStr,endDateStr,true,monitors,1);

		StringBuffer allScpoolStatus = new StringBuffer();
		for (Scpool scpool : scLst) {
			int sellAllOut = 0;
			String status = "";
			String endstatus ="";


			int sore = 0;
			String code = scpool.getCode();

			if(code.equals("600019")){
				System.out.println("600019");
			}

			if(code!=null && code.length()>1){

			}else{
				continue;
			}

			String codeStr  = scpool.getCode().substring(0, 3);
			if(codeStr.equals("900")||codeStr.equals("200")){
				continue;
			}
			if(scpool.getCode().equals("002782")){
				System.out.println("002782");
			}

			if(scpool!=null)
			{
				boolean PERCENTASC = true;
				
				// 1 天 当天
				List<HistorytradeInfo> resultList2Days  = historytradeInfoRepository.findHistorytradeInfoByDesc4MA30_MA60_ASC(scpool.getCode(), startDate100DaysBeforeStr, endDateStr);
				// 止盈
				double buyPrice = 0.0;
				boolean isStartBuy = false;

				double isSellPrice= 0.0;
				boolean isSelled =  false;
				// 出票前一天的量
                double previousVol = 0.0;
                // 出票当天的量
                double currentVol = 0.0;
                double currentPrice = 0.0;
                int buyDays = 0;
				for(HistorytradeInfo historytradeInfo: resultList2Days){

					if(sellAllOut==1){
						break;
					}

					if(historytradeInfo.getTrade_date().equals(scpool.getCreateTime())){
						currentVol = historytradeInfo.getVol();
						currentPrice = historytradeInfo.getClose();
						isStartBuy = true;
						buyDays = 1;
						continue;
					}

					if(!isStartBuy){
						continue;
					}

					// 出票前一天的量
					if(buyDays==0){
						previousVol = historytradeInfo.getVol();
					}

					// 出票第二天，买入第一天
					if(buyDays==1){
						buyPrice = historytradeInfo.getOpen();
					}

					buyDays++;

					if(isStartBuy && historytradeInfo.getTrade_date().equals(endDateStr) ){
						double rat = (historytradeInfo.getClose()  - buyPrice) / buyPrice * 100;
						// 缩量0.5倍，极佳买点
						double ratio = (historytradeInfo.getClose()-currentPrice)/currentPrice *100;
						if(historytradeInfo.getVol()*3<=currentVol  && ratio<-4 ){
							// 清仓
							if(StringUtils.isEmpty(status)){
								status = historytradeInfo.getTrade_date()+":缩量0.5倍 - 极佳买点";
							} else {
								status = status + "|"+ historytradeInfo.getTrade_date()+":缩量0.5倍 - 极佳买点";
							}
							endstatus =  scpool.getCreateTime() +":缩量0.5倍 - 加仓";
//							sellAllOut = 1;
							PERCENTASC = false;
							break;
						}

					}

				}


				if(!PERCENTASC){
					// 更新 status  和  sellAllout
					System.out.println(scpool.getName()+"  "+ scpool.getCode() + "  "+scpool.getCreateTime() +"  " + scpool.getMonitor()  +" \n" );
					if(!endstatus.equals("")){
						allScpoolStatus.append(scpool.getName()+"  "+ scpool.getCode()  +"  m:"+ scpool.getMonitor()  + "   "+endstatus +"   <br> <br>  "  );
					}
					Scpool scpoolNew = new Scpool(scpool.getName(),scpool.getCode(), "999991",endDateStr,"",0.0);
					scpoolRepository.save(scpoolNew);

					continue;
				}
			}
		}

		// 发email
		helper.sendEmail(allScpoolStatus.toString(),999991,endDateStr);

//		System.out.println(">>>>>>>>>>>>>>>>>> result.size="+Results.size());
//		for (stockCode result : Results) {
////			List<scpool> sc = scpoolRepository.findOrderReqByName(result.getNAME());
////
//			System.out.println("Code:"+result.getSYMBOL()+"  name:"+result.getNAME()+ "   monitor:"+result.getPE()
//			+"   createTime:"+result.getVOLUME()+ "    sore:"+result.getPROFIT()+ "    lowestPriceDays:"+result.getSYMBOL());
////
////			if(sc!=null && sc.size()>0){
////				scpool scpool = sc.get(sc.size()-1);
////
////				String createTime = scpool.getCreateTime();
////				Date histroy = format.parse(createTime);
////				Date nowDate = format.parse(endDateStr);
////
////				if((nowDate.getTime()-histroy.getTime())/(1000*60*60*24)<20)
////				{
////					continue;
////				}else{
////					scpool scpoolnew = new scpool(result.getNAME(),result.getSYMBOL(), endDateStr);
////					scpoolRepository.save(scpoolnew);
////				}
////
////			}else {
//
//			    String endDate100DaysAfterStr = helper.getStartDateStr360DaysAfter(endDateStr); // 要当天
//			    List<historytradeInfo> resultList2Days = historytradeInfoRepository.findHistorytradeInfo2(result.getSYMBOL(),endDateStr,endDate100DaysAfterStr);
//			    if(resultList2Days!=null && resultList2Days.size()>0){
//			    	historytradeInfo historytradeInfo = resultList2Days.get(0);
//					scpool scpool = new scpool(result.getNAME(),result.getSYMBOL(), "8858",endDateStr,historytradeInfo.getTrade_date(),historytradeInfo.getOpen());
//					scpoolRepository.save(scpool);
//			    }
////			}
//
//
//		}
		
		
		return Results;

	}
	
	
	
	
//	@Scheduled(cron = "0 37 20 3 * ?")
	// middle and small 
	public List<StockCode> firstStrategy() throws Exception
	{
		List<StockCode> Results = new ArrayList();
		List<StockCode> queryResults = null;
//		try {
//
			queryResults = stockCodeRepository.findAllOrderReq();
//
//			for (stockCode result : queryResults) {
//				if(result.getTCAP()>20000000000.00)
//				{
//					Results.add(result);
//				}
//			}
//		}catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			LoggingUtils.log(Level.INFO, logger, e.getMessage(), null);
//		}finally
//		{}
		return queryResults;
	}
	
	// big 
	public List<StockCode> secondStrategy() throws Exception
	{
		List<StockCode> Results = new ArrayList();
		try {
			List<StockCode> queryResults = stockCodeRepository.findAllOrderReq();
			
			for (StockCode result : queryResults) {
				if(Results.size()<100)
				{
					if(result.getMFRATIO2()!=null && result.getMFRATIO2().indexOf(".")>0
							&& result.getMCAP()!=null && result.getMCAP().indexOf(".")>0
							&& result.getPE()!=null && result.getPE().indexOf(".")>0){
						if(Double.parseDouble(result.getMFRATIO2())>0)
						{
							if(Double.parseDouble(result.getMCAP())>50000000000.00 
									&& Double.parseDouble(result.getPE())<30)
							{
								Results.add(result);
								//System.out.println("Code:"+result.getCode()+"  name:"+result.getName()+ "   PE:"+result.getPE()+"   MFRATIO2:"+result.getMFRATIO2());
							}
						}
					}else
					{
						continue;
					}
					
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{}
		return Results;
	}


	// big
	public List<Stockbasics> thirdStrategy() throws Exception
	{
		List<Stockbasics> queryResults = new ArrayList();
		try {
			queryResults = stockbasicsRepository.findstockByRevAndProfit(-100,-100);

//			for (stockCode result : queryResults) {
//				if(Results.size()<100)
//				{
//					if(result.getMFRATIO2()!=null && result.getMFRATIO2().indexOf(".")>0
//							&& result.getMCAP()!=null && result.getMCAP().indexOf(".")>0
//							&& result.getPE()!=null && result.getPE().indexOf(".")>0){
//						if(Double.parseDouble(result.getMFRATIO2())>0)
//						{
//							if(Double.parseDouble(result.getMCAP())>50000000000.00
//									&& Double.parseDouble(result.getPE())<30)
//							{
//								Results.add(result);
//								//System.out.println("Code:"+result.getCode()+"  name:"+result.getName()+ "   PE:"+result.getPE()+"   MFRATIO2:"+result.getMFRATIO2());
//							}
//						}
//					}else
//					{
//						continue;
//					}
//
//				}
//			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{}
		return queryResults;
	}
	
	
	private double computeAvgeVolume(List<Stock> stockList) throws Exception
	{
		double sum = (double) 0.0;
		int i = 0;
		for (Stock result : stockList) {
			
			if(result.getVOLUME()!=null && result.getVOLUME().indexOf(".")>0)
			{
				sum = sum + Double.parseDouble(result.getVOLUME());
			}else
			{
				continue;
			}
			i++;
		}
		
		return sum/i;
	}
	
	
	
	
	private double computeAvgePrive(List<Stock> stockList)throws Exception
	{
		double sum = (double) 0.0; 
		int i = 0;
		for (Stock result : stockList) {
			if(result.getCLOSE()!=null && result.getCLOSE().indexOf(".")>0)
			{
				sum = sum + Double.parseDouble(result.getCLOSE());
			}else
			{
				continue;
			}
			i++;
		}
		
		return sum/i;
	}
	
	
	private double computeHighestPrice(List<Stock> stockList)throws Exception
	{
		double hightPrivce = 0.0;
		for (Stock result : stockList) {
			if(result.getHIGHESTPRICE()!=null && result.getHIGHESTPRICE().indexOf(".")>0)
			{
				if(hightPrivce<Double.parseDouble(result.getHIGHESTPRICE()))
				{
					hightPrivce = Double.parseDouble(result.getHIGHESTPRICE());
				}
			}else
			{
				continue;
			}
		}
		
		return hightPrivce;
	}
	
	private Map computeLowestPrice(List<Stock> stockList)throws Exception
	{
		double lowestPrivce = 0.0;
		Date time = null;
		int i=0;
		for (Stock result : stockList) {
			if(result.getLOWESTPRICE()!=null && result.getLOWESTPRICE().indexOf(".")>0)
			{
				if(i==0){
					lowestPrivce =  Double.parseDouble(result.getLOWESTPRICE());
				}else
				{
					if(lowestPrivce>Double.parseDouble(result.getLOWESTPRICE()))
					{
						lowestPrivce = Double.parseDouble(result.getLOWESTPRICE());
						time = result.getTIME();
					}
				}
				
			}else
			{
				continue;
			}
			i++;
		}
		
		Map lowestPriceWithTime = new HashMap();
		lowestPriceWithTime.put(0+"",lowestPrivce+"");
		lowestPriceWithTime.put(1+"",time);
		
		return lowestPriceWithTime;
	}
	
	private double computeLowestVolume(List<Stock> stockList)throws Exception
	{
		double lowestVolume = 0.0;
		int i=0;
		for (Stock result : stockList) {
			if(result.getVOLUME()!=null && result.getVOLUME().indexOf(".")>0)
			{
				if(i==0){
					lowestVolume =  Double.parseDouble(result.getVOLUME());
				}else
				{
					if(lowestVolume>Double.parseDouble(result.getVOLUME()))
					{
						lowestVolume = Double.parseDouble(result.getVOLUME());
					}
				}
				
			}else
			{
				continue;
			}
			i++;
		}
		
		return lowestVolume;
	}
	
	
	private double computeMA(List<Stock> stockList, int days)throws Exception
	{
		double maSUM =0.0;
		int i=0;
		for (Stock result : stockList) {
			if(i==days)
			{
				break;
			}
			if(result.getCLOSE()!=null && result.getCLOSE().indexOf(".")>0)
			{
				maSUM = maSUM+  Double.parseDouble(result.getCLOSE());
				i++;
			}
			
		}
		
		if(i<days)
		{
			days = i;
		}
		
		return maSUM/days;
	}
	
	
	private double computeVolumnMA(List<Stock> stockList, int days)throws Exception
	{
		double maSUM =0.0;
		int i=0;
		for (Stock result : stockList) {
			if(i==days)
			{
				break;
			}
			if(result.getVOLUME()!=null && result.getVOLUME().indexOf(".")>0)
			{
				maSUM = maSUM+  Double.parseDouble(result.getVOLUME());
				i++;
			}
		}
		
		if(i<days)
		{
			days = i;
		}
		
		return maSUM/days;
	}
	
	
	
	
	private double computeAvgeHS(List<Stock> stockList)throws Exception
	{
		double sum = (double) 0.0; 
		int i = 0;
		for (Stock result : stockList) {
			if(result.getHS()!=null && result.getHS().indexOf(".")>0)
			{
				sum = sum + Double.parseDouble(result.getHS());
			}else
			{
				continue;
			}
			i++;
		}
		
		return sum/i;
	}
	
	private double computeAvgePERCENT(List<Stock> stockList)throws Exception
	{
		double sum = (double) 0.0; 
		int i=0;
		for (Stock result : stockList) {
			if(result.getPERCENT()!=null && result.getPERCENT().indexOf(".")>0){
				sum = sum + Double.parseDouble(result.getPERCENT());
			}else
			{
				continue;
			}
			i++;
		}
		
		return sum/i;
	}
	
	
	private double computeSumPERCENT(List<Double> doubleList)throws Exception
	{
		double sum = (double) 0.0; 
		for (Double result : doubleList) {
			if(result!=null){
				sum = sum + result;
			}else
			{
				continue;
			}
		}
		
		return sum;
	}
	
	private int comuteRSI(Double positiveListValue,Double negativeListValue)throws Exception
	{
		Double valueRS = positiveListValue/negativeListValue ;
		int valueRSI = (int) (100 - 100/(1+valueRS));
		return valueRSI;
	}
	
	
	private String getStartDateStr()
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd");
		Calendar now = Calendar.getInstance();  
		System.out.println(now.get(Calendar.DAY_OF_WEEK));
		
		if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			now.add(Calendar.DAY_OF_MONTH, -3);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			now.add(Calendar.DAY_OF_MONTH, -4);
		}else{
			now.add(Calendar.DAY_OF_MONTH, -2);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
		System.out.println(dateStr);
		return dateStr;
	}
	
	private String getStartDateStr10DaysBefore()
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd");
		Calendar now = Calendar.getInstance();  
		System.out.println(now.get(Calendar.DAY_OF_WEEK));
		
		if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			now.add(Calendar.DAY_OF_MONTH, -17);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			now.add(Calendar.DAY_OF_MONTH, -18);
		}else{
			now.add(Calendar.DAY_OF_MONTH, -16);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
		System.out.println(dateStr);
		return dateStr;
	}
}
