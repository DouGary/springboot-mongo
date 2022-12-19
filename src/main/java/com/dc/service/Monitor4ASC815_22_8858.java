package com.dc.service;


import com.dc.dal.*;
import com.dc.model.*;
import com.dc.utils.Helper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.*;

import com.dc.utils.Helper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class Monitor4ASC815_22_8858 {
	@Autowired
	private StockCodeRepository stockCodeRepository;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private ScpoolRepository scpoolRepository;

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
	
	Logger logger = LogManager.getLogger(Monitor4ASC815_22_8858.class);

	public List<StockCode> getFinal(String endDateStr) throws Exception
	{
		
		List<StockCode> Results = new ArrayList();

//		List<stockCode> sCode = firstStrategy();

//		List<stockCode> sCode = helper.secondStrategy();

//		List<stockbasics> stockbasicsLst = thirdStrategy();

		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");

//		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd");
//
//		if(endDateStr==null || endDateStr.equals("")){
//			endDateStr = format.format(new Date());
//		}
//		String startDateStr = helper.getStartDateStr(endDateStr);

//		Date endDate = format.parse(endDateStr);  // 不要当天
//		Date startDate = format.parse(startDateStr); // 要当天

//		String startDate10DaysBeforeStr = helper.getStartDateStr10DaysBefore(endDateStr); // 要当天
//		Date startDate10DaysBeforeNow = format.parse(startDate10DaysBeforeNowStr); // 要当天
//		String startDate100DaysBeforeStr = helper.getStartDateStr100DaysBefore(endDateStr); // 要当天
		String startDate100DaysBeforeStr = helper.getStartDateStr60daysBefore(endDateStr); // 要当天
        List<String> monitors = new ArrayList<>();
        monitors.add("88");
		List<Scpool> scLst = scpoolRepository.findAllScpoolGteEndDateStr(startDate100DaysBeforeStr,endDateStr,false,monitors);


		for (Scpool scpool : scLst) {
			int sore = 0;
			String code = scpool.getCode();
			if(code!=null && code.length()>1){

			}else{
//				profitability profitability = profitabilityRepository.findOrderReqByName(stockbasics.getName());
				continue;
//				if(profitability==null){
//					System.out.println(stockbasics.getName());
//					continue;
//				}else{
//					stockbasics.setCode(profitability.getCode());
//					stockbasicsRepository.updateCodeByName(profitability.getCode(),stockbasics.getName());
//				}

			}

			String codeStr  = scpool.getCode().substring(0, 3);
			if(codeStr.equals("900")||codeStr.equals("200")){
				continue;
			}

//			if(endDateStr.equals("2017-3-1") && result.getNAME().equals("申通快递")){
//				System.out.println("300176");
//			}
//
			if(scpool.getCode().equals("002782")){
				System.out.println("002782");
			}

//			if(endDateStr.equals("2017-10-16") && result.getSYMBOL().equals("300176")){
//				System.out.println("300176");
//			}



			
			if(scpool!=null)
			{
				boolean PERCENTASC = true;
				
				// 1 天 当天
				List<HistorytradeInfo> resultList2Days  = historytradeInfoRepository.findHistorytradeInfoByDesc4MA30_MA60(scpool.getCode(), startDate100DaysBeforeStr, endDateStr);

//				if(resultList2Days.size()<250){
//					continue;
//				}


				if(resultList2Days==null 
						|| resultList2Days.size()<=20
						|| resultList2Days.get(0)==null 
						|| resultList2Days.get(0).getTrade_date()==null
						|| !resultList2Days.get(0).getTrade_date().equals(endDateStr)){
					continue;
				}

				double highest = helper.computeHighestHighPrice4kdj(resultList2Days,50);
				double lowest  = helper.computeLowestLowPrice4kdj(resultList2Days,50);
				double percentLow =((lowest - highest)/highest)*100;
//				if(percentLow>-20){
//					continue;
//				}

				int days4His = 0;
				double previousDaysMa240 = 0.0;
				double previousDaysMa120 = 0.0;





//				historytradeInfo historytradeInfoDay0 = resultList2Days.get(0);
//				historytradeInfo historytradeInfoDay1 = resultList2Days.get(1);
//				if(historytradeInfoDay0.getMa120()<historytradeInfoDay1.getMa120()){
//					continue;
//				}

//				int days = 0;
//				double p_change = 0.0;
//				for(historytradeInfo historytradeInfo : resultList2Days ){
//					if(days==3){
//						break;
//					}
//
//					if(historytradeInfo.getV_ma5()<historytradeInfo.getV_ma10() || historytradeInfo.getP_change()<0 ){
//						PERCENTASC = false;
//						break;
//					}
//					p_change = p_change + historytradeInfo.getP_change();
//					if(days==2){
//						if(p_change>15){
//							PERCENTASC = false;
//							break;
//						}
//					}
//
//					days++;
//				}


				
				HistorytradeInfo historytradeInfo = resultList2Days.get(0);
				String startDate = helper.getStartDateStr60daysBefore(historytradeInfo.getTrade_date());
				List<Stkdj> queryResults = stkdjRepository.findSTKDJByCodeAndBeforeDate(historytradeInfo.getTs_code(), historytradeInfo.getTrade_date(),startDate);


				
				if(queryResults==null
						|| queryResults.size()<=20){
					continue;
				}

//				if(historytradeInfo.getP_change()<6){
//					continue;
//				}

				int days = 0;
				double bollMidPrevious = 0.0;
				double p_change_total = 0.0;
//				boolean rsiFlag = false;

				double closePrevious = 0.0;
				double highPrevious = 0.0;
				double lowPrevious = 0.0;

				boolean isFirstKDJGolden = false;
				boolean isTG = false;
				boolean isBoolUpDown = false;
				boolean isBelowMiddle = false;

				int aboveBollUpDays = 0;
				for(Stkdj stkdj : queryResults ){
//					if(days==15){
//						break;
//					}

					if(stkdj.getTrade_date().equals(scpool.getCreateTime())){
						if(days<4 || days>15){
//						if(days>15){
							PERCENTASC = false;
							break;
						}
						break;
					}

//					int closeIndex = 0;
//					List<Double> closelst = new ArrayList<Double>();
//					for(historytradeInfo historytradeInfo3 : resultList2Days){
//
//						if(closeIndex==days  || closeIndex>days){
//							closelst.add(historytradeInfo3.getClose());
//						}
//						closeIndex++;
//					}


//					double bollMid = helper.getAverage(20,closelst);
//					double std = helper.getStandardDevition(20,closelst,bollMid);
//					double bollUp = bollMid+ 2*std;
//					double bollBottom = bollMid - 2*std;
//					System.out.println(historytradeInfo2.getCode() +":" +"  bollMid="+bollMid+"  bollUp"+bollUp+"   bollBottom="+bollBottom);





					if(days==0){

						if(stkdj.getLow()<=stkdj.getBoll()){
							isBelowMiddle = true;
						}


						if(!stkdj.getTrade_date().equals(endDateStr)){
							PERCENTASC = false;
							break;
						}
//						HashMap<String, Double>  macdMap = helper.getMACD(closelst,12,26,9);
//						double dif = macdMap.get("DIF");
//						double dea = macdMap.get("DEA");
//						double macd = macdMap.get("MACD");
//						System.out.println(historytradeInfo2.getCode() +":" +"  DIF="+dif+"  dea"+dea+"   macd="+macd);


//						if(stkdj!=null && (stkdj.getKdjk()>80 || stkdj.getKdjd()>80) ){
//							PERCENTASC = false;
//							break;
//						}
//						if(stkdj!=null && stkdj.getKdjj()>=stkdj.getKdjd() &&stkdj.getKdjk()>=stkdj.getKdjd() ){
//						}else{
//							PERCENTASC = false;
//							break;
//						}

//						if(stkdj.getMacd() < stkdj.getMacds()  ){
//							PERCENTASC = false;
//							break;
//						}

                        if(stkdj.getLow()/stkdj.getBoll()<1.009 && stkdj.getLow()>stkdj.getBoll_lb() && stkdj.getClose()>stkdj.getBoll()){

						}else{
							PERCENTASC = false;
							break;
						}

						p_change_total += stkdj.getPct_chg();

						bollMidPrevious = stkdj.getBoll();

						closePrevious = stkdj.getClose();
						highPrevious = stkdj.getHigh();
						lowPrevious = stkdj.getLow();

						if(stkdj.getLow()<stkdj.getBoll_lb() && stkdj.getHigh()>stkdj.getBoll_ub()){
							isBoolUpDown = true;
						}

					}else{

//						if(days==1){
//							if(stkdj.getMacd() > stkdj.getMacds()
//									|| stkdj.getKdjj() > stkdj.getKdjd()
//									|| stkdj.getKdjk() > stkdj.getKdjd()
//							){
//								PERCENTASC = false;
//								break;
//							}
//						}


						if(days==1){

							if(!isBelowMiddle){
								if(stkdj.getLow()<=stkdj.getBoll()){
									isBelowMiddle = true;
								}
							}

							p_change_total += stkdj.getPct_chg();
							if(p_change_total>20){
								PERCENTASC = false;
								break;
							}



							if(stkdj.getKdjj() < stkdj.getKdjd()
									&& stkdj.getKdjk() < stkdj.getKdjd()){
								isFirstKDJGolden = true;
							}

							// 跳空高开
							if(stkdj.getHigh() < lowPrevious ){
								isTG = true;
							}



						}

						if(stkdj.getLow()/stkdj.getBoll()>1.009 && stkdj.getLow()>stkdj.getBoll()){

						}else{
							PERCENTASC = false;
							break;
						}

//						if(!isBelowMiddle){
//							PERCENTASC = false;
//							break;
//						}

						if(isFirstKDJGolden && days>1 && days<7){
							if(stkdj.getKdjj() < stkdj.getKdjd()
									&& stkdj.getKdjk() < stkdj.getKdjd()){
								isFirstKDJGolden = true;
							}else{
								isFirstKDJGolden = false;
							}
						}

                        if(stkdj.getHigh()>stkdj.getBoll_ub() ){

//                        	if(days>28){
//								aboveBollUpDays++;
//							}else{

//								PERCENTASC = false;
//								break;
//							}

//                        	if(aboveBollUpDays>=2){
//								PERCENTASC = false;
//								break;
//							}
                        }

//						if(!rsiFlag){
//							if(stkdj.getRsi_6()<20){
//								rsiFlag = true;
//							}
//						}

//						if(days==1 ){
//						    if(bollMidPrevious>stkdj.getBoll()){
//								bollMidPrevious = stkdj.getBoll();
//							}else{
//								PERCENTASC = false;
//								break;
//							}
//						}else{
//							if(bollMidPrevious>=stkdj.getBoll()){
//								bollMidPrevious = stkdj.getBoll();
//							}else{
//								PERCENTASC = false;
//								break;
//							}
//						}
//
//						if(stkdj!=null && stkdj.getKdjj()<stkdj.getKdjd() &&stkdj.getKdjk()<stkdj.getKdjd() ){
//						}else{
//							PERCENTASC = false;
//							break;
//						}

						if(bollMidPrevious>stkdj.getBoll()){
							bollMidPrevious = stkdj.getBoll();
						}else{
							PERCENTASC = false;
							break;
						}

					}

//					if(stkdj.getMacd() <= stkdj.getMacds()  ){
//						PERCENTASC = false;
//						break;
//					}
//
//					if(stkdj.getDma()<0  ){
//						PERCENTASC = false;
//						break;
//					}






					
					days++;
				}

				if(isFirstKDJGolden){
					sore += 10000;
				}

				// 跳空高开
				if(isTG){
					sore += 10000;
				}

				if(isBoolUpDown){
					sore += 10000;
				}

//				if(!rsiFlag){
//					PERCENTASC = false;
//					continue;
//				}

//				List<boll> stbollList = stbollRepository.findSTBollByCodeAndLteDate(historytradeInfo.getCode(),endDateStr);
//				if(stbollList==null || stbollList.size()<10){
//					PERCENTASC = false;
//					break;
//				}
//				if(stbollList.get(0).getCode().equals("002811")){
//					System.out.println("002811");
//				}
//
//				if(stbollList==null || stbollList.size()<10){
//					PERCENTASC = false;
//					break;
//				}else{
//					int bollIndex = 1;
//					double bollMidPrevious = 0.0;
//					for(boll stboll : stbollList ){
//						if(bollIndex<=2){
//							if(bollIndex==1){
//								bollMidPrevious = stboll.getMiddle();
////								if(historytradeInfo.getClose()<bollMidPrevious){
////									PERCENTASC = false;
////									break;
////								}
//							}else{
//								if(bollIndex==2 ){
//								    if(bollMidPrevious>stboll.getMiddle()){
//										bollMidPrevious = stboll.getMiddle();
//									}else{
//										PERCENTASC = false;
//										break;
//									}
//								}
////							    else{
////									if(bollMidPrevious>stboll.getMiddle()){
////										PERCENTASC = false;
////										break;
////									}else{
////										bollMidPrevious = stboll.getMiddle();
////									}
////								}
//							}
//						}else{
//							break;
//						}
//						bollIndex++;
//					}
//				}

//
//				stmacd stmacd = stmacdRepository.findSTMACDByCodeAndDate(historytradeInfo.getCode(), historytradeInfo.getDate());
//				if(stmacd!=null && stmacd.getMacd() > stmacd.getMacds()  ){
//				}else{
//					PERCENTASC = false;
//				}
				
				if(PERCENTASC)
				{


//					stockCode result = new stockCode();
//					result.setSYMBOL(stockbasics.getCode());
//
//					result.setNAME(stockbasics.getName());
//					result.setCODE(stockbasics.getCode());
//					result.setPE(stockbasics.getPe()+"");
//					result.setVOLUME(stockbasics.getPb()+"");
//					result.setMFRATIO2(stockbasics.getRev()+"");
//					result.setPROFIT(stockbasics.getProfit()+"");
//					Results.add(result);


					StockCode result = new StockCode();
					result.setSYMBOL(scpool.getCode());

					result.setNAME(scpool.getName());
					result.setCODE(scpool.getCode());
					result.setPE(scpool.getMonitor());
					result.setVOLUME(scpool.getCreateTime());
					result.setMFRATIO2(scpool.getCreateTime());
					result.setPROFIT(sore+"");
					Results.add(result);


				}
				
				
			}
		}
		
		
		System.out.println(">>>>>>>>>>>>>>>>>> result.size="+Results.size());
		for (StockCode result : Results) {
//			List<scpool> sc = scpoolRepository.findOrderReqByName(result.getNAME());
//			
			System.out.println("Code:"+result.getSYMBOL()+"  name:"+result.getNAME()+ "   monitor:"+result.getPE()
			+"   createTime:"+result.getVOLUME()+ "    sore:"+result.getPROFIT()+ "    lowestPriceDays:"+result.getSYMBOL());
//
//			if(sc!=null && sc.size()>0){
//				scpool scpool = sc.get(sc.size()-1);
//
//				String createTime = scpool.getCreateTime();
//				Date histroy = format.parse(createTime);
//				Date nowDate = format.parse(endDateStr);
//
//				if((nowDate.getTime()-histroy.getTime())/(1000*60*60*24)<20)
//				{
//					continue;
//				}else{
//					scpool scpoolnew = new scpool(result.getNAME(),result.getSYMBOL(), endDateStr);
//					scpoolRepository.save(scpoolnew);
//				}
//
//			}else {
			
//			    String endDate100DaysAfterStr = helper.getStartDateStr360DaysAfter(endDateStr); // 要当天
//			    List<historytradeInfo> resultList2Days = historytradeInfoRepository.findHistorytradeInfo2(result.getSYMBOL(),endDateStr,endDate100DaysAfterStr);
//			    if(resultList2Days!=null && resultList2Days.size()>0){
//			    	historytradeInfo historytradeInfo = resultList2Days.get(0);
//					scpool scpool = new scpool(result.getNAME(),result.getSYMBOL(), "8858",endDateStr,historytradeInfo.getTrade_date(),historytradeInfo.getOpen());
			        Scpool scpool = new Scpool(result.getNAME(),result.getSYMBOL(), "8858",endDateStr,"",0.0);
					scpoolRepository.save(scpool);
//			    }
//			}
			
			
		}
		
		
		return Results;
//		System.out.println(resultList.size());

//
////		List<scpool> sCode = scpoolRepository.findAllScpool();
//
//		List<stockCode> Results = new ArrayList();
//		try {
//
//			SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd");
//
//			if(endDateStr==null || endDateStr.equals("")){
//				endDateStr = format.format(new Date());
//			}
//			String startDateStr = helper.getStartDateStr(endDateStr);
//
//			Date endDate = format.parse(endDateStr);  // 不要当天
//			Date startDate = format.parse(startDateStr); // 要当天
//
//			String startDate10DaysBeforeStr = helper.getStartDateStr10DaysBefore(endDateStr); // 要当天
//
//			String startDate5DaysBeforeNowStr = helper.getStartDateStr5DaysBeforeNow(endDateStr); // 要当天
//			Date startDate5DaysBeforeNow = format.parse(startDate5DaysBeforeNowStr); // 要当天
//
//			String startDate10DaysBeforeNowStr = helper.getStartDateStr10DaysBeforeNow(endDateStr); // 要当天
//			Date startDate10DaysBeforeNow = format.parse(startDate10DaysBeforeNowStr); // 要当天
//
//			String startDate20DaysBeforeNowStr = helper.getStartDateStr20DaysBeforeNow(endDateStr); // 要当天
//			Date startDate20DaysBeforeNow = format.parse(startDate20DaysBeforeNowStr); // 要当天
//
//			String startDate30DaysBeforeNowStr = helper.getStartDateStr30DaysBeforeNow(endDateStr); // 要当天
//			Date startDate30DaysBeforeNow = format.parse(startDate30DaysBeforeNowStr); // 要当天
//
//
//
//
//			Date startDate10DaysBefore = format.parse(startDate10DaysBeforeStr); // 要当天
//			Date startDate5DaysBefore = format.parse(startDate10DaysBeforeStr); // 要当天
//j
//			for (stockCode result : sCode) {
//
//				List<stock> stockList5DaysBeforeNow = stockRepository.findAllOrderReq(result.getNAME(), startDate5DaysBeforeNow, endDate);
//				double ma5 = helper.computeMA(stockList5DaysBeforeNow);
//
//
//				List<stock> stockList10DaysBeforeNow = stockRepository.findAllOrderReq(result.getNAME(), startDate10DaysBeforeNow, endDate);
//				double ma10 = helper.computeMA(stockList10DaysBeforeNow);
//
//
//				List<stock> stockList20DaysBeforeNow = stockRepository.findAllOrderReq(result.getNAME(), startDate20DaysBeforeNow, endDate);
//				double ma20 = helper.computeMA(stockList20DaysBeforeNow);
//
//
//				List<stock> stockList30DaysBeforeNow = stockRepository.findAllOrderReq(result.getNAME(), startDate30DaysBeforeNow, endDate);
//				double ma30 = helper.computeMA(stockList30DaysBeforeNow);
//
//				if(ma5>ma10 && ma10>ma20 && ma20>ma30 )
//				{
//
//				}else{
//					continue;
//				}
////
//
//				if(result!=null)
//				{
//					boolean PERCENTASC = true;
//					try{
//						List<stock> stockList = stockRepository.findAllOrderReq(result.getNAME(), startDate, endDate);
//
////						System.out.println("startDate10DaysBefore:"+startDate10DaysBefore+"  startDate:"+startDate);
//
//						List<stock> stockList10DaysBefore = stockRepository.findAllOrderReq(result.getNAME(), startDate10DaysBefore, startDate);
//
//						double volumn12days10MA = computeVolumnMA(stockList10DaysBefore,10);
//
//						if(stockList!=null )
//						{
//							if(stockList10DaysBefore==null || stockList10DaysBefore.size()<7)
//							{
//								PERCENTASC = false;
//							}
//
//							if(PERCENTASC){
//								int i=1;
//								double day1Open = 0.0;
//								double dayClose = 0.0;
//								double dayPercent = 0.0;
//								for(stock stockResult : stockList10DaysBefore)
//								{
//									if(i==1 && stockResult.getOPEN()!=null && stockResult.getOPEN().indexOf(".")>0 && !stockResult.getOPEN().equals("0.0"))
//									{
//										day1Open = Double.parseDouble(stockResult.getOPEN());
//									}else
//									{
//										PERCENTASC = false;
//										break;
//									}
//
//									if(stockResult.getCLOSE()!=null && stockResult.getCLOSE().indexOf(".")>0 && !stockResult.getCLOSE().equals("0.0"))
//									{
//										dayClose = Double.parseDouble(stockResult.getCLOSE());
//										if((dayClose-day1Open)/day1Open>0.03 || (dayClose-day1Open)/day1Open < -0.03 ){
//											PERCENTASC = false;
//											break;
//										}
//									}else
//									{
//										PERCENTASC = false;
//										break;
//									}
//
//									if(stockResult.getPERCENT()!=null && stockResult.getPERCENT().indexOf(".")>0 && !stockResult.getPERCENT().equals("0.0"))
//									{
//										dayPercent = Double.parseDouble(stockResult.getPERCENT());
//										if(dayPercent>2 ||dayPercent<-2 ){
//											PERCENTASC = false;
//											break;
//										}
//									}else
//									{
//										PERCENTASC = false;
//										break;
//									}
//
//
//									if(stockResult.getVOLUME()!=null && stockResult.getVOLUME().indexOf(".")>-1 && !stockResult.getVOLUME().equals("0.0"))
//									{
//										if(Double.parseDouble(stockResult.getVOLUME())/volumn12days10MA>3 || volumn12days10MA/Double.parseDouble(stockResult.getVOLUME())>3 ){
//											PERCENTASC = false;
//											break;
//										}
//									}else
//									{
//										PERCENTASC = false;
//										break;
//									}
//								}
//							}
//
//
//							if(PERCENTASC){
//								double day1Close = 0.0;
//								double day1Open = 0.0;
//								double day2Close = 0.0;
//								double day2Open = 0.0;
//
//								int i=1;
//								for(stock stockResult : stockList)
//								{
//									if(i==1 && stockResult.getCLOSE()!=null && stockResult.getCLOSE().indexOf(".")>0
//											)
//									{
//										day1Close = Double.parseDouble(stockResult.getCLOSE());
//										day1Open = Double.parseDouble(stockResult.getOPEN());
//
//										if(day1Open>day1Close){
//											PERCENTASC = false;
//											break;
//										}
//									}
//
//									if(i==2 && stockResult.getCLOSE()!=null && stockResult.getCLOSE().indexOf(".")>0)
//									{
//										day2Close = Double.parseDouble(stockResult.getCLOSE());
//										day2Open = Double.parseDouble(stockResult.getOPEN());
//										if((day2Close-day1Open)/day1Open<0.03)
//										{
//											PERCENTASC = false;
//											break;
//										}
//
//										if(day2Open>day2Close){
//											PERCENTASC = false;
//											break;
//										}
//									}
//
//									if(stockResult.getPERCENT()!=null && stockResult.getPERCENT().indexOf(".")>-1)
//									{
//										if(Double.parseDouble(stockResult.getPERCENT())<-3 || Double.parseDouble(stockResult.getPERCENT())>3 ){
//											PERCENTASC = false;
//											break;
//										}
//									}
//
//
//									if(stockResult.getVOLUME()!=null && stockResult.getVOLUME().indexOf(".")>-1)
//									{
//										double volumeGrouth = Double.parseDouble(stockResult.getVOLUME())/volumn12days10MA;
//										if(volumeGrouth<1.5 || volumeGrouth>3){
//											PERCENTASC = false;
//											break;
//										}
//									}
//									i++;
//								}
//							}
//
//
//							if(PERCENTASC)
//							{
//								Results.add(stockCodeRepository.findOrderReqByName(result.getNAME()));
//							}
//						}
//
//					}catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						LoggingUtils.log(Level.INFO, logger, e.getMessage(), null);
//					}
//
//				}
////				i++;
//			}
//
//
//			System.out.println(">>>>>>>>>>>>>>>>>> result.size="+Results.size());
//			for (stockCode result : Results) {
////				List<scpool> sc = scpoolRepository.findOrderReqByName(result.getNAME());
////				if(sc==null || sc.size()==0){
//					scpool scpool = new scpool(result.getNAME(),result.getSYMBOL(), endDateStr);
//					scpoolRepository.save(scpool);
////				}
//
//				System.out.println("Code:"+result.getSYMBOL()+"  name:"+result.getNAME()+ "   PE:"+result.getPE()
//				+"   MFRATIO2:"+result.getMFRATIO2()+ "    PROFIT:"+result.getPROFIT()+ "    lowestPriceDays:"+result.getSYMBOL());
//			}
//		}catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			LoggingUtils.log(Level.INFO, logger, e.getMessage(), null);
//		}finally
//		{}
//		return Results;
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
