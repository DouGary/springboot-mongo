package com.dc.service;


import com.dc.dal.*;
import com.dc.model.*;
import com.dc.utils.Helper;
import com.dc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class Monitor_99999 {
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

	Logger logger = LogManager.getLogger(Monitor_99999.class);

	public List<StockCode> getFinal(String endDateStr) throws Exception
	{
		
		List<StockCode> Results = new ArrayList();


		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");

		String startDate100DaysBeforeStr = helper.getStartDateStr15daysBefore(endDateStr,-15); // 要当天
        List<String> monitors = new ArrayList<>();
        // 88884，888884，8828，881，8818，8878，88781
		monitors.add("888846");
		monitors.add("8888461");
		monitors.add("8888464");
		monitors.add("88884641");
		monitors.add("88884");
		monitors.add("888884");
//		monitors.add("8828");
//		monitors.add("88");
		monitors.add("881");
		monitors.add("8818");
//		monitors.add("8838");
//		monitors.add("88381");
		monitors.add("889");
//		monitors.add("8898");
//		monitors.add("8868");
		monitors.add("8878");
		monitors.add("88781");
		monitors.add("88782");

		List<Scpool> scLst = scpoolRepository.findAllScpoolGteEndDateStrAndSellAllOut(startDate100DaysBeforeStr,endDateStr,true,monitors,1);

		StringBuffer allScpoolStatus = new StringBuffer();
		for (Scpool scpool : scLst) {
			int sellAllOut = 0;
			String status = "";
			String endstatus ="";


			int sore = 0;
			String code = scpool.getCode();

			if(code.equals("300346")){
				System.out.println("300346");
			}

			if(code!=null && code.length()>1){

			}else{
				continue;
			}

			String codeStr  = scpool.getCode().substring(0, 3);
			if(codeStr.equals("900")||codeStr.equals("200")){
				continue;
			}

			if(scpool!=null)
			{
				boolean PERCENTASC = true;
				
				// 1 天 当天
				List<HistorytradeInfo> resultList2Days  = historytradeInfoRepository.findHistorytradeInfoByDesc4MA30_MA60_ASC(scpool.getCode(), startDate100DaysBeforeStr, endDateStr);

				HistorytradeInfo historytradeInfo1 = resultList2Days.get(0);
				String startDate = helper.getStartDateStr60daysBefore(historytradeInfo1.getTrade_date());
				List<Stkdj> queryResults = stkdjRepository.findSTKDJByCodeAndBeforeDateASC(historytradeInfo1.getTs_code(), startDate100DaysBeforeStr, endDateStr);

				// 止盈
				double buyPrice = 0.0;
				boolean isStartBuy = false;

				double isSellPrice= 0.0;
				boolean isSelled =  false;
				// 出票前一天的量
                double previousVol = 0.0;
                // 出票当天的量
                double currentVol = 0.0;
                int buyDays = 0;
				for(HistorytradeInfo historytradeInfo: resultList2Days){

					if(sellAllOut==1){
						break;
					}

					if(historytradeInfo.getTrade_date().equals(scpool.getCreateTime())){
						currentVol = historytradeInfo.getVol();
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

					if(isStartBuy&& historytradeInfo.getTrade_date().equals(endDateStr) ){
						double rat = (historytradeInfo.getClose()  - buyPrice) / buyPrice * 100;
						if(rat > 8){
							// 止盈 卖出记录
							isSellPrice = historytradeInfo.getClose();
							isSelled = true;
							if(StringUtils.isEmpty(status)){
								status = historytradeInfo.getTrade_date()+"收益大于8个点 - 分批止盈";
								endstatus = scpool.getCreateTime() +"收益大于8个点 - 分批止盈";
							} else {
								status = status + "|"+ historytradeInfo.getTrade_date()+"收益大于8个点 - 分批止盈";
								endstatus = scpool.getCreateTime()+"收益大于8个点 - 分批止盈";
							}
						} else if(rat > 20 && historytradeInfo.getTrade_date().equals(endDateStr) ){
							// 清仓
							if(StringUtils.isEmpty(status)){
								status = historytradeInfo.getTrade_date()+":收益大于20个点止盈 - 清仓";
								endstatus = scpool.getCreateTime()+":收益大于20个点止盈 - 清仓";
							} else {
								status = status + "|"+ historytradeInfo.getTrade_date()+":收益大于20个点止盈 - 清仓";
								endstatus =  scpool.getCreateTime()+":收益大于20个点止盈 - 清仓";
							}
							sellAllOut = 1;
							PERCENTASC = false;
							break;
						}


						// 加仓
						if(historytradeInfo.getClose()<=historytradeInfo.getMa60() || historytradeInfo.getClose()<=historytradeInfo.getMa240()){
							// 加仓
							if(StringUtils.isEmpty(status)){
								status = historytradeInfo.getTrade_date()+":回调到ma60或ma250加仓";
								endstatus = scpool.getCreateTime() + "|"+ endstatus +"收益大于8个点 - 分批止盈";
							} else {
								status = status + "|"+ historytradeInfo.getTrade_date()+":回调到ma60或ma250加仓";
								endstatus =  scpool.getCreateTime() +":回调到ma60或ma250加仓";
							}
						}

						// 缩量清仓
						if(historytradeInfo.getVol()*2<currentVol){
							// 清仓
							if(StringUtils.isEmpty(status)){
								status = historytradeInfo.getTrade_date()+":缩量0.5倍 - 加仓";
								endstatus =  scpool.getCreateTime() +":缩量0.5倍 - 加仓";
							} else {
								status = status + "|"+ historytradeInfo.getTrade_date()+":缩量0.5倍 - 加仓";
								endstatus =  scpool.getCreateTime() +":缩量0.5倍 - 加仓";
							}
//							sellAllOut = 1;
//							PERCENTASC = false;
//							break;
						}
						// 清仓
						// 放量，放出了出票当天量的1.5倍
						if(historytradeInfo.getVol()>currentVol*2){
							// 长上影线
							if(historytradeInfo.getHigh()/historytradeInfo.getClose()>1.04){
								// 清仓
								if(StringUtils.isEmpty(status)){
									status = historytradeInfo.getTrade_date()+":放量2倍长上影线 - 清仓";
								} else {
									status = status + "|"+ historytradeInfo.getTrade_date()+":放量2倍长上影线 - 清仓";
								}
								endstatus =  scpool.getCreateTime() +":放量2倍长上影线 - 清仓";
								sellAllOut = 1;
								PERCENTASC = false;
								break;
							}
						}
					}


					int days = 0;
					// macd 柱子
					double previousMacdh = 0.0;
					boolean isBuyed = false;
					boolean isContinue = false;
					double previousBoll = 0.0;
					for(Stkdj stkdj : queryResults ){
//					}

						if(!historytradeInfo.getTrade_date().equals(stkdj.getTrade_date())){
							previousMacdh = stkdj.getMacdh();
							previousBoll = stkdj.getBoll();
							if( stkdj.getTrade_date().equals(scpool.getCreateTime())  ){
								isBuyed = true;
								continue;
							}
							continue;
						}

						if( stkdj.getTrade_date().equals(scpool.getCreateTime())  ){
							isBuyed = true;
							previousMacdh = stkdj.getMacdh();
							previousBoll = stkdj.getBoll();
							continue;
						}

						// 持有 设置持有标识记库
						boolean ishold = false;
						if(isBuyed  && historytradeInfo.getTrade_date().equals(endDateStr) ){
							if(previousMacdh <= stkdj.getMacdh() ){
								previousMacdh = stkdj.getMacdh();
								// 持有
								if(StringUtils.isEmpty(status)){
									status = historytradeInfo.getTrade_date()+":macd柱递增持有";
								} else {
									status = status + "|"+ historytradeInfo.getTrade_date()+":macd柱递增持有";
								}
								endstatus =  scpool.getCreateTime() +":macd柱递增持有";
							} else {
								previousMacdh = stkdj.getMacdh();
//								// 持有
							}

							// boll 中轨递减，清仓
							if(previousBoll> stkdj.getBoll()){
								// 清仓
								if(StringUtils.isEmpty(status)){
									status = historytradeInfo.getTrade_date()+":boll中轨递减 - 清仓";
								} else {
									status = status + "|"+ historytradeInfo.getTrade_date()+":boll中轨递减 - 清仓";
								}
								endstatus =  scpool.getCreateTime() +":boll中轨递减 - 清仓";
								sellAllOut = 1;
								PERCENTASC = false;
								break;
							} else {
								previousBoll = stkdj.getBoll();
							}

							// 加仓
							if(stkdj.getLow()<=stkdj.getBoll() || stkdj.getLow()<=stkdj.getBoll_lb()){
								// 加仓
								if(StringUtils.isEmpty(status)){
									status = historytradeInfo.getTrade_date()+":回调boll中轨或下轨加仓";
								} else {
									status = status + "|"+ historytradeInfo.getTrade_date()+":回调boll中轨或下轨加仓";
								}
								endstatus =  scpool.getCreateTime() +":回调boll中轨或下轨加仓";
							}

							// 减仓
							if(stkdj.getHigh()>=stkdj.getBoll_ub() && stkdj.getPct_chg()>5 ){
								// 减仓
								if(StringUtils.isEmpty(status)){
									status = historytradeInfo.getTrade_date()+":boll上轨且当天涨幅超过5个点减仓";
								} else {
									status = status + "|"+ historytradeInfo.getTrade_date()+":boll上轨且当天涨幅超过5个点减仓";
								}
								endstatus =  scpool.getCreateTime() +":boll上轨且当天涨幅超过5个点减仓";
							}

							// macd高位(macds>=1) 死叉(macds>=macd,即macd慢线下穿macd快线) 清仓
							if(stkdj.getMacds()>=1 && stkdj.getMacds()>=stkdj.getMacd()){
								// 清仓
								if(StringUtils.isEmpty(status)){
									status = historytradeInfo.getTrade_date()+":macd高位死叉清仓";
								} else {
									status = status + "|"+ historytradeInfo.getTrade_date()+":macd高位死叉清仓";
								}
								endstatus =  scpool.getCreateTime() +":macd高位死叉清仓";
								sellAllOut = 1;
								PERCENTASC = false;
								break;
							}


						}

						if(historytradeInfo.getTrade_date().equals(stkdj.getTrade_date())){
							break;
						}
						days++;
					}
				}


				if(!PERCENTASC){
					// 更新 status  和  sellAllout
					scpool.setSellAllOut(sellAllOut);
					scpool.setStatus(scpool.getStatus()+"|"+status);
					if(!endstatus.equals("")){
						allScpoolStatus.append(scpool.getName()+"  "+ scpool.getCode()  +"  m:"+ scpool.getMonitor()  + "   "+endstatus +"   <br> <br>  "  );
					}
					System.out.println(scpool.getName()+"  "+ scpool.getCode() +"  m:"+ scpool.getMonitor() + "  createTime:"+scpool.getCreateTime() + "   "+scpool.getStatus() +" \n" );
					scpoolRepository.updateStatusAndSellAllOutScpoolById(scpool);
					continue;
				}



			}


			// 更新 status  和  sellAllout
			scpool.setSellAllOut(sellAllOut);
			scpool.setStatus(scpool.getStatus()+"|"+status);
			if(!endstatus.equals("")){
				allScpoolStatus.append(scpool.getName()+"  "+ scpool.getCode() +"  m:"+ scpool.getMonitor() + "   "+ endstatus +" <br><br> "  );
			}



			System.out.println(scpool.getName()+"  "+ scpool.getCode() +"  m:"+ scpool.getMonitor() + "  createTime:"+scpool.getCreateTime()  + "   "+scpool.getStatus() +" \n" );
			scpoolRepository.updateStatusAndSellAllOutScpoolById(scpool);

		}

		if(!allScpoolStatus.equals("")){
			// 发email
			helper.sendEmail(allScpoolStatus.toString(),99999,endDateStr);
		}


		
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

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{}
		return queryResults;
	}
}
