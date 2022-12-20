package com.dc.service;


import com.dc.dal.*;
import com.dc.model.*;
import com.dc.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Monitor_888887 {
	@Autowired
	private StockCodeRepository stockCodeRepository;

	@Autowired
	private StockjjcgphRepository stockjjcgphRepository;

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
	private DailyBasicProRepository dailyBasicProRepository;

	@Autowired
	private LongTouIndexProRepository longTouIndexProRepository;


	@Autowired
	private Helper helper;

	Logger logger = LogManager.getLogger(Monitor_888887.class);

	public List<StockCode> getFinal(String endDateStr) throws Exception {

		List<StockCode> Results = new ArrayList();

		Map<String, Integer> resultMap = new HashMap<>();


		List<StockCode> sCode = helper.firstStrategy();


		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		String startDate100DaysBeforeStr = helper.get20DaysBefore(endDateStr); // 要当天


		for (StockCode stockjjcgph : sCode) {
			boolean is300 = false;
			int sore = 0;
			String code = stockjjcgph.getSYMBOL();
			if (code != null && code.length() > 1) {

			} else {
				continue;

			}

			String codeStr = stockjjcgph.getSYMBOL().substring(0, 3);
			if (codeStr.equals("900") || codeStr.equals("200")) {
				continue;
			}
			if (stockjjcgph.getSYMBOL().equals("001270")) {
				System.out.println("001270");
			}
			if (stockjjcgph != null) {
				boolean PERCENTASC = true;

				String codeWithSHSZ = "";
				String codePrefix = stockjjcgph.getSYMBOL().substring(0, 3);
				if (codePrefix.equals("000") || codePrefix.equals("002") || codePrefix.equals("300")) {
					codeWithSHSZ = stockjjcgph.getSYMBOL() + ".SZ";
				} else {
					codeWithSHSZ = stockjjcgph.getSYMBOL() + ".SH";
				}

				if(codePrefix.equals("300")){
					is300 = true;
				}


//				List<dailyBasicPro> dailyBasicProList = dailyBasicProRepository.findOrderReqByStockcodeAndDate(codeWithSHSZ, startDate100DaysBeforeStr, endDateStr);
//
//				if (dailyBasicProList.size() < 5) {
//					continue;
//				}
//
//				int days4DailyBasic = 0;
//				boolean isContinue = false;
//				for (dailyBasicPro dailyBasicPro : dailyBasicProList) {
//					if (days4DailyBasic == 5) {
//						break;
//					}
//
//					if (days4DailyBasic == 0) {
//						if (dailyBasicPro.getTurnover_rate() < 8 || dailyBasicPro.getTurnover_rate() > 20) {
//							PERCENTASC = false;
//							isContinue = true;
//							break;
//						}
//					} else {
//						if (dailyBasicPro.getTurnover_rate() > 4.2) {
//							PERCENTASC = false;
//							isContinue = true;
//							break;
//						}
//					}
//					days4DailyBasic++;
//				}
//
//				if (isContinue) {
//					continue;
//				}

				// 1 天 当天
				List<HistorytradeInfo> resultList2Days = historytradeInfoRepository.findHistorytradeInfoByDesc4MA30_MA60(stockjjcgph.getSYMBOL(), startDate100DaysBeforeStr, endDateStr);
				int days4His = 0;
				double firstDayVol = 0.0;
                int ban = 0;
                boolean isContinue = false;
				for (HistorytradeInfo historytradeInfo : resultList2Days) {


					if (days4His == 15) {
						isContinue = true;
						break;
					}


					if(is300){
						if(historytradeInfo.getPct_chg()>18){
							ban++;
						} else {
							isContinue = true;
							break;
						}
					} else {
						if(historytradeInfo.getPct_chg()>9.8){
							ban++;
						} else {
							isContinue = true;
							break;
						}
					}
					days4His++;
				}

				if(ban>=2){
					resultMap.put(resultList2Days.get(0).getCode(),ban);
				}

				if(isContinue){
					continue;
				}


			}







//			System.out.println(">>>>>>>>>>>>>>>>>> result.size=" + Results.size());
//			for (stockCode result : Results) {
//				System.out.println("Code:" + result.getSYMBOL() + "  name:" + result.getNAME() + "   PE:" + result.getPE()
//						+ "   MFRATIO2:" + result.getMFRATIO2() + "    sore:" + result.getPROFIT() + "    lowestPriceDays:" + result.getSYMBOL());
//
//				String endDate100DaysAfterStr = helper.getStartDateStr360DaysAfter(endDateStr); // 要当天
//				List<historytradeInfo> resultList2Days = historytradeInfoRepository.findHistorytradeInfo2(result.getSYMBOL(), endDateStr, endDate100DaysAfterStr);
//				if (resultList2Days != null && resultList2Days.size() > 0) {
//					historytradeInfo historytradeInfo = resultList2Days.get(0);
//					scpool scpool = new scpool(result.getNAME(), result.getSYMBOL(), "888886", endDateStr, historytradeInfo.getTrade_date(), historytradeInfo.getOpen());
//					scpoolRepository.save(scpool);
//				}
//			}
		}

		if(resultMap.size()>0){
			resultMap = helper.hashMapSort(resultMap);

//			longTouIndexPro longTouIndexPro = new longTouIndexPro();
//
//			int index = 0;
//			for(Map.Entry<String,Integer> entry : resultMap.entrySet()) {
//				index = entry.getValue();
//				break;
//			}
//			longTouIndexPro.setIndex(index);
//			longTouIndexPro.setIndexContent(resultMap.toString());
//			longTouIndexPro.setTrade_date(endDateStr);
//
//			longTouIndexProRepository.save(longTouIndexPro);

			System.out.println(resultMap.toString());

//			if(!allScpoolStatus.equals("")){
				// 发email
				helper.sendEmail(resultMap.toString(),888887,endDateStr);
//			}

		} else {
			System.out.println(">>>>>>>>>>>>>>>>>> result.size=0");
		}

		return Results;
	}

}
