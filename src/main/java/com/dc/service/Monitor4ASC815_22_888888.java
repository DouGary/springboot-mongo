package com.dc.service;



import com.dc.dal.*;
import com.dc.model.*;
import com.dc.utils.Helper;
import com.dc.utils.Helper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Monitor4ASC815_22_888888 {
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
	private longTouIndexProRepository longTouIndexProRepository;

	@Autowired
	private Helper helper;

	Logger logger = LogManager.getLogger(Monitor4ASC815_22_888888.class);

	public List<StockCode> getFinal(String endDateStr) throws Exception {

		List<StockCode> Results = new ArrayList();

		Map<String, Integer>  resultMap = new HashMap<>();

		// 多头连板个数
		Map<String, Integer> resultMapCount = new HashMap<>();
		// 空头吃面个数
		Map<String, Integer> resultSellMap = new HashMap<>();
		List<String> sellList = new ArrayList<>();


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
				String codePrefix = stockjjcgph.getSYMBOL().substring(0, 3);

				if(codePrefix.equals("300") || codePrefix.equals("688")){
					is300 = true;
				}

				// 1 天 当天
				List<HistorytradeInfo> resultList2Days = historytradeInfoRepository.findHistorytradeInfoByDesc4MA30_MA60(stockjjcgph.getSYMBOL(), startDate100DaysBeforeStr, endDateStr);
				int days4His = 0;
				double firstDayVol = 0.0;
                int ban = 0;
                boolean isContinue = false;
                int days=0;
				for (HistorytradeInfo historytradeInfo : resultList2Days) {

					if(days==0 && endDateStr.equals(historytradeInfo.getTrade_date()) ){
						double sellRation =  (historytradeInfo.getHigh()-historytradeInfo.getClose())/historytradeInfo.getClose()*100;
						if(sellRation>8){
							sellList.add(historytradeInfo.getCode());
						}
                    }else {
						if (days==0){
							break;
						}
					}
					days++;

//					if (days4His == 15) {
//						isContinue = true;
//						break;
//					}


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

//		resultMapCount.put(endDateStr,resultMap.size());
//		resultSellMap.put(endDateStr,sellList.size());

		System.out.println(endDateStr+"   buy:"+resultMap.size()+"   sell:"+sellList.size());
		resultMap = helper.hashMapSort(resultMap);
		System.out.println(resultMap.toString());

		if(resultMap.size()>0){
			resultMap = helper.hashMapSort(resultMap);

			LongTouIndexPro longTouIndexPro = new LongTouIndexPro();

			int index = 0;
			for(Map.Entry<String,Integer> entry : resultMap.entrySet()) {
				index = entry.getValue();
				break;
			}
			longTouIndexPro.setIndex(index);
			longTouIndexPro.setIndexContent(resultMap.toString());
			longTouIndexPro.setBuy(resultMap.size());
			longTouIndexPro.setSell(sellList.size());
			longTouIndexPro.setTrade_date(endDateStr);

			longTouIndexProRepository.save(longTouIndexPro);

//			System.out.println(resultMap.toString());

//			if(!allScpoolStatus.equals("")){
				// 发email
				helper.sendEmail(endDateStr+"   buy:"+resultMap.size()+"   sell:"+sellList.size() +" <br> "+ resultMap.toString(),888888,endDateStr);
//			}

		} else {
			System.out.println(">>>>>>>>>>>>>>>>>> result.size=0");
		}

		return Results;
	}

}
