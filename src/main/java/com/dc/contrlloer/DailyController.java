package com.dc.contrlloer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.dc.dal.IndexCodeDataRepository;
import com.dc.model.IndexCodeData;
import com.dc.model.StockCode;

import com.dc.service.*;
import com.dc.service.Monitor_8878;
import com.dc.utils.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/daily")
public class DailyController {
	
	
//	@Autowired
//	stFundInfoRepository stFundInfoRepository;


	@Autowired
	private IndexCodeDataRepository indexCodeDataRepository;

	@Autowired
	Monitor_8878 monitor_8878;

	@Autowired
	Monitor4ASC815_22_881 monitor4ASC815_22_881;

	@Autowired
	Monitor_8848 monitor_8848;

	@Autowired
	Monitor_8858 monitor_8858;
	@Autowired
	Monitor4ASC815_22_88884 monitor4ASC815_22_88884;

	@Autowired
	Monitor4ASC815_22_8888464 monitor4ASC815_22_8888464;

	@Autowired
	Monitor_88884641 monitor_88884641;

	@Autowired
	Monitor4ASC815_22_88781 monitor4ASC815_22_88781;



	@Autowired
	Monitor_888884 monitor_888884;

	@Autowired
	Monitor_888887 monitor_888887;
	@Autowired
	Monitor_888888 monitor_888888;

	@Autowired
	Monitor_99999 monitor_99999;

	@Autowired
	Monitor_999991 monitor_999991;

	@Autowired
	Monitor_999992 monitor_999992;

	@Autowired
	Monitor_999993 monitor_999993;


	@Autowired
	Monitor_9999931 monitor_9999931;

//	@Autowired
//	Monitor4ASC815_22_9999931 monitor4ASC815_22_9999931;

	@Autowired
	Monitor_999994 monitor_999994;

	@Autowired
	Monitor_99881 monitor_99881;

//	@Autowired
//	Monitor4ASC815_22_99881 monitor4ASC815_22_99881;
//
//	@Autowired
//	Monitor4Test2 monitor4Test2;
	
	@Autowired
	private Helper helper;
	
//	@Autowired
//	Sender sender;

//	@Autowired
//	ScpoolService scpoolService;
	
	Logger logger = LogManager.getLogger(DailyController.class);


//	@Scheduled(cron = "0 0 5 * * ?")
	@RequestMapping(value = "senderTest", method = RequestMethod.GET)
	public void senderTest() {
		try{

//			monitor4ASC815_22_30.getFinal("");

//			String endDateStr="";
//			Date dt = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			endDateStr=sdf.format(dt);
//			boolean isSendEmailEveryDay = false;
//
			String endDateStr = "20220628";
			boolean isSendEmailEveryDay = true;
//


//
//			endDateStr = helper.getPreviouseWorkDay(endDateStr);
			SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");

			Date now1 = new Date();
			String nowStr = format.format(now1);

		    while(true){

//		    	if(endDateStr.equals("20210103")){
//		    		break;
//				}

				if(isSendEmailEveryDay){
					nowStr = endDateStr;
				}


//				System.out.println("monitor4ASC815_22_661 and date="+endDateStr);
//				List<stockCode> Results661 = monitor4ASC815_22_661.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results661, 661, endDateStr);
//				}



//				this.longTermIndexRisk(nowStr,endDateStr);
//				this.shortTermIndexRisk(nowStr,endDateStr);
//
//				// 突破 第一板
//				System.out.println("monitor4ASC815_22_8888464 and date="+endDateStr);
//				List<StockCode> Results8888464old = monitor4ASC815_22_8888464.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8888464old, 8888464, endDateStr);
//				}

				// 突破 第一板
//				System.out.println("monitor4ASC815_22_88884641 and date="+endDateStr);
//				List<StockCode> Results8888464 = monitor_88884641.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8888464, 88884641, endDateStr);
//				}
//
				System.out.println("monitor4ASC815_22_8878 and date="+endDateStr);
				List<StockCode> Results8878 = monitor_8878.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8878, 8878, endDateStr);
//				}

				//   只有量的约束，底部放量
//				System.out.println("monitor4ASC815_22_88781 and date="+endDateStr);
//				List<StockCode> Results88781 = monitor4ASC815_22_88781.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88781, 88781, endDateStr);
//				}

				// vol/v_ma5>2
				System.out.println("monitor4ASC815_22_881 and date="+endDateStr);
				List<StockCode> Results881 = monitor4ASC815_22_881.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results881, 881, endDateStr);
//				}

//				// 底部 第一板 反包
//				System.out.println("monitor4ASC815_22_8888884 and date="+endDateStr);
//				List<StockCode> Results888884 = monitor_888884.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888884, 888884, endDateStr);
//				}
//
//				//				// 底部 第一板 反包
//				System.out.println("monitor4ASC815_22_88884 and date="+endDateStr);
//				List<StockCode> Results88884 = monitor4ASC815_22_88884.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88884, 88884, endDateStr);
//				}
//
//				// 不中断涨停板统计
//				System.out.println("monitor4ASC815_22_888887 and date="+endDateStr);
//				List<StockCode> Results888887 = monitor_888887.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888887, 888887, endDateStr);
//				}
//
//				System.out.println("monitor4ASC815_22_888888 and date="+endDateStr);
//				List<StockCode> Results888888 = monitor_888888.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888888, 888888, endDateStr);
//				}
//
//				//  对所有策略的观察，回打中轨
//				System.out.println("monitor4ASC815_22_8848 and date="+endDateStr);
//				List<StockCode> Results8848 = monitor_8848.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8848, 8848, endDateStr);
//				}
//
//				//  对所有策略的观察，回打中轨
//				System.out.println("monitor4ASC815_22_8858 and date="+endDateStr);
//				List<StockCode> Results8858 = monitor_8858.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8858, 8858, endDateStr);
//				}

				// 99999 - 跟踪策略
//				System.out.println("monitor4ASC815_22_99999 and date="+endDateStr);
//				List<StockCode> Results99999 = monitor_99999.getFinal(endDateStr);
//				// 99999 - 跟踪策略 缩量三分之一
//				System.out.println("monitor4ASC815_22_999991 and date="+endDateStr);
//				List<StockCode> Results999991 = monitor_999991.getFinal(endDateStr);
//				// 排序
//				System.out.println("monitor4A SC815_22_999992 and date="+endDateStr);
//				List<StockCode> Results999992 = monitor_999992.getFinal(endDateStr);
//				// 上升趋势
//				System.out.println("monitor4A SC815_22_999993 and date="+endDateStr);
//				List<StockCode> Results999993 = monitor_999993.getFinal(endDateStr);
//				// 上升趋势
//				System.out.println("monitor4A SC815_22_9999931 and date="+endDateStr);
//				List<StockCode> Results9999931 = monitor_9999931.getFinal(endDateStr);
//				// 上升趋势且回调到中轨附近
//				System.out.println("monitor4A SC815_22_999994 and date="+endDateStr);
//				List<StockCode> Results999994 = monitor_999994.getFinal(endDateStr);
//				// 监控881，boll.mid重新递增
//				System.out.println("monitor4A SC815_22_99881 and date="+endDateStr);
//				List<StockCode> Results99881 = monitor_99881.getFinal(endDateStr);








				//
//				//				//				// 突破boll上轨
//				System.out.println("monitor4ASC815_22_88 and date="+endDateStr);
//				List<stockCode> Results88 = monitor4ASC815_22_88.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
////					sendEmailWithDate(Results88, 88, endDateStr);
//				}
//
//				//				System.out.println("monitor4ASC815_22_88784 and date="+endDateStr);
//				List<stockCode> Results88784 = monitor4ASC815_22_88784.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
////					sendEmailWithDate(Results88784, 88784, endDateStr);
//				}




//// 可中断涨停板统计
//				System.out.println("monitor4ASC815_22_7761 and date="+endDateStr);
//				List<stockCode> Results7761 = monitor4ASC815_22_7761.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results7761, 7761, endDateStr);
//				}


//
//
//				// -7 +7 反包
//				System.out.println("monitor4ASC815_22_88883 and date="+endDateStr);
//				List<stockCode> Results88883 = monitor4ASC815_22_88883.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88883, 88883, endDateStr);
//				}

//				// 底部 第一板 反包
//				System.out.println("monitor4ASC815_22_88884 and date="+endDateStr);
//				List<stockCode> Results88884 = monitor4ASC815_22_88884.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88884, 88884, endDateStr);
//				}
//
//				// 底部 第一板 反包
//				System.out.println("monitor4ASC815_22_8888884 and date="+endDateStr);
//				List<stockCode> Results888884 = monitor4ASC815_22_888884.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888884, 888884, endDateStr);
//				}









// 突破 第一板
//				System.out.println("monitor4ASC815_22_8888461 and date="+endDateStr);
//				List<stockCode> Results8888461 = monitor4ASC815_22_8888461.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8888461, 8888461, endDateStr);
//				}


//  对所有策略的观察，回打中轨
//				System.out.println("monitor4ASC815_22_8848784 and date="+endDateStr);
//				List<stockCode> Results8848784 = monitor4ASC815_22_8848784.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8848784, 8848784, endDateStr);
//				}

				// 第一个板后，6天内缩量0.5
//				System.out.println("monitor4ASC815_22_88785 and date="+endDateStr);
//				List<stockCode> Results88785 = monitor4ASC815_22_88785.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88785, 88785, endDateStr);
//				}

//                // 突破 第一板
//				System.out.println("monitor4ASC815_22_888846 and date="+endDateStr);
//				List<stockCode> Results888846 = monitor4ASC815_22_888846.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888846, 888846, endDateStr);
//				}
//				// 突破 第一板
//				System.out.println("monitor4ASC815_22_8888461 and date="+endDateStr);
//				List<stockCode> Results8888461 = monitor4ASC815_22_8888461.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8888461, 8888461, endDateStr);
//				}
//
//				// 突破 第一板
//				System.out.println("monitor4ASC815_22_8888462 and date="+endDateStr);
//				List<stockCode> Results8888462 = monitor4ASC815_22_8888462.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8888462, 8888462, endDateStr);
//				}
//
//				// 突破 第一板
//				System.out.println("monitor4ASC815_22_8888463 and date="+endDateStr);
//				List<stockCode> Results8888463 = monitor4ASC815_22_8888463.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8888463, 8888463, endDateStr);
//				}
//
//				// 突破 第一板
//				System.out.println("monitor4ASC815_22_8888464 and date="+endDateStr);
//				List<stockCode> Results8888464 = monitor4ASC815_22_8888464.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8888464, 8888464, endDateStr);
//				}
//
//				System.out.println("monitor4ASC815_22_8888465 and date="+endDateStr);
//				List<stockCode> Results8888465 = monitor4ASC815_22_8888465.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8888465, 8888465, endDateStr);
//				}
//
//
//
//
//
//				// 底部 长下阴线
//				System.out.println("monitor4ASC815_22_88885 and date="+endDateStr);
//				List<stockCode> Results88885 = monitor4ASC815_22_88885.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88885, 88885, endDateStr);
//				}
//
//
//
//				// 底部 第一板 反包
//				System.out.println("monitor4ASC815_22_8888884 and date="+endDateStr);
//				List<stockCode> Results888884 = monitor4ASC815_22_888884.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888884, 888884, endDateStr);
//				}
//
//				// 突破boll上轨
//				System.out.println("monitor4ASC815_22_88 and date="+endDateStr);
//				List<stockCode> Results88 = monitor4ASC815_22_88.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88, 88, endDateStr);
//				}
//

//
//				// 40days, 量3>vol/v_ma5>1.5；macd》-0.1; high/boll_up>0.98
//				System.out.println("monitor4ASC815_22_8828 and date="+endDateStr);
//				List<stockCode> Results8828 = monitor4ASC815_22_8828.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8828, 8828, endDateStr);
//				}
//
//
//
//				System.out.println("monitor4ASC815_22_8818 and date="+endDateStr);
//				List<stockCode> Results8818 = monitor4ASC815_22_8818.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8818, 8818, endDateStr);
//				}
//
//				// 50days, 量；macd》-0.0007
//				System.out.println("monitor4ASC815_22_8868 and date="+endDateStr);
//				List<stockCode> Results8868 = monitor4ASC815_22_8868.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8868, 8868, endDateStr);
//				}
//
//				System.out.println("monitor4ASC815_22_8878 and date="+endDateStr);
//				List<stockCode> Results8878 = monitor4ASC815_22_8878.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8878, 8878, endDateStr);
//				}
//
//
//				 // 图谱boll中轨
//				System.out.println("monitor4ASC815_22_888 and date="+endDateStr);
//				List<stockCode> Results888 = monitor4ASC815_22_888.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888, 888, endDateStr);
//				}
//
//
//
//				// 568 向上跳空高开
//				System.out.println("monitor4ASC815_22_889 and date="+endDateStr);
//				List<stockCode> Results889 = monitor4ASC815_22_889.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results889, 889, endDateStr);
//				}
//
//				// 对889的观察策略
//				System.out.println("monitor4ASC815_22_8891 and date="+endDateStr);
//				List<stockCode> Results8891 = monitor4ASC815_22_8891.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8891, 8891, endDateStr);
//				}
//
//				//  88881 - 18天ma5<ma10,第一天ma5>ma10
//				System.out.println("monitor4ASC815_22_88881 and date="+endDateStr);
//				List<stockCode> Results88881 = monitor4ASC815_22_88881.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88881, 88881, endDateStr);
//				}
//
//				 // 底部 第一板 反包
//				System.out.println("monitor4ASC815_22_888849 and date="+endDateStr);
//				List<stockCode> Results888849 = monitor4ASC815_22_888849.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888849, 888849, endDateStr);
//				}
//
//				// 88882 - 28天boll.mid递减,底部连续两天放量vol/vol_5>2
//				System.out.println("monitor4ASC815_22_88882 and date="+endDateStr);
//				List<stockCode> Results88882 = monitor4ASC815_22_88882.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88882, 88882, endDateStr);
//				}
//
// 				// 28days, 量；macd》-0.0007
//				System.out.println("monitor4ASC815_22_8898 and date="+endDateStr);
//				List<stockCode> Results8898 = monitor4ASC815_22_8898.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8898, 8898, endDateStr);
//				}
//
//
//
//
//
//				System.out.println("monitor4ASC815_22_88782 and date="+endDateStr);
//				List<stockCode> Results88782 = monitor4ASC815_22_88782.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88782, 88782, endDateStr);
//				}
//
//                // 28days, macd < -0.1 ; jjcg>=6的568只票
//				System.out.println("monitor4ASC815_22_8838 and date="+endDateStr);
//				List<stockCode> Results8838 = monitor4ASC815_22_8838.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8838, 8838, endDateStr);
//				}
//
//				// 40days, macd < -0.1 ; vol>ma_vol*1.5;jjcg>=6的568只票
//				System.out.println("monitor4ASC815_22_88381 and date="+endDateStr);
//				List<stockCode> Results88381 = monitor4ASC815_22_88381.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88381, 88381, endDateStr);
//				}
//
//
//                // 对88策略的观察，回打中轨
//				System.out.println("monitor4ASC815_22_88181 and date="+endDateStr);
//				List<stockCode> Results88181 = monitor4ASC815_22_88181.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88181, 88181, endDateStr);
//				}























//				System.out.println("monitor4ASC815_22_88783 and date="+endDateStr);
//				List<stockCode> Results88783 = monitor4ASC815_22_88783.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88783, 88783, endDateStr);
//				}

				// 换手率 5<turnover<10
//				System.out.println("monitor4ASC815_22_888886 and date="+endDateStr);
//				List<stockCode> Results888886 = monitor4ASC815_22_888886.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results888886, 888886, endDateStr);
//				}

//				System.out.println("monitor4ASC815_22_88886 and date="+endDateStr);
//				List<stockCode> Results88886 = monitor4ASC815_22_88886.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88886, 88886, endDateStr);
//				}

				// 统计464策略10%收益率以上的次数
//				System.out.println("monitor4A SC815_22_999995 and date="+endDateStr);
//				List<stockCode> Results999995 = monitor4ASC815_22_999995.getFinal(endDateStr);

//				System.out.println("monitor4A SC815_22_999996 and date="+endDateStr);
//				List<stockCode> Results999996 = monitor4ASC815_22_999996.getFinal(endDateStr);




//				System.out.println("monitor4ASC815_22_88382 and date="+endDateStr);
//				List<stockCode> Results88382 = monitor4ASC815_22_88382.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results88382, 88382, endDateStr);
//				}






// lowestVol
//				System.out.println("monitor4ASC815_22_8808 and date="+endDateStr);
//				List<stockCode> Results8808 = monitor4ASC815_22_8808.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results8808, 8808, endDateStr);
//				}


				// 突破boll上轨 // 只要TCAP > 200亿
//				System.out.println("monitor4ASC815_22_886 and date="+endDateStr);
//				List<stockCode> Results886 = monitor4ASC815_22_886.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results886, 886, endDateStr);
//				}

				// 在886的基础上，macds< -1.0
//				System.out.println("monitor4ASC815_22_887 and date="+endDateStr);
//				List<stockCode> Results887 =monitor4ASC815_22_887.getFinal("886",endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results887, 887, endDateStr);
//				}



//				monitor4ASC815_22_998_RPS.getFinal(endDateStr);

//				// 第一天成交量异动，多头排列，boll.mid,boll.up 递增，但是boll.lb递减
//				// 最好是 ma250和ma60递增，close>ma250 & close>ma60
//				System.out.println("monitor4ASC815_22_109 and date="+endDateStr);
//				List<stockCode> Results109 = monitor4ASC815_22_109.getFinal(endDateStr);
////				if(nowStr.equals(endDateStr)){
////				sendEmailWithDate(Results109, 109, endDateStr);
////				}
//
//				// 第一天成交量异动，多头排列，boll.mid,boll.up 递增，但是boll.lb递减
//				System.out.println("monitor4ASC815_22_108 and date="+endDateStr);
//				List<stockCode> Results108 = monitor4ASC815_22_108.getFinal(endDateStr);
////				if(nowStr.equals(endDateStr)){
//				sendEmailWithDate(Results108, 108, endDateStr);
////				}
//
//
//				// 30天，第一天封板。
//				System.out.println("monitor4ASC815_22_107 and date="+endDateStr);
//				List<stockCode> Results107 = monitor4ASC815_22_107.getFinal(endDateStr);
////				if(nowStr.equals(endDateStr)){
//				sendEmailWithDate(Results107, 107, endDateStr);
////				}
//
//				// 30天，第一天boll.mid 开始递增
//				System.out.println("monitor4ASC815_22_106 and date="+endDateStr);
//				List<stockCode> Results106 = monitor4ASC815_22_106.getFinal(endDateStr);
////				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results106, 106, endDateStr);
////				}






//
//
////				// 突破boll上轨 macd<-0.001 即可 但 vol/vma5>2.1
////				System.out.println("monitor4ASC815_22_100 and date="+endDateStr);
//				List<stockCode> Results100 = monitor4ASC815_22_100.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results100, 100, endDateStr);
//				}
//
//				//
//				// 突破上轨，macd < 0.0001
//				System.out.println("monitor4ASC815_22_101 and date="+endDateStr);
//				List<stockCode> Results101 = monitor4ASC815_22_101.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results101, 101, endDateStr);
//				}
//
////
////				// 突破中轨，macd < 0.0001
//				System.out.println("monitor4ASC815_22_102 and date="+endDateStr);
//				List<stockCode> Results102 = monitor4ASC815_22_102.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results102, 102, endDateStr);
//				}








//
//
//	            //  clse>all line；ma60递增
//				System.out.println("monitor4ASC815_22_99 and date="+endDateStr);
//				List<stockCode> Results99 = monitor4ASC815_22_99.getFinal(endDateStr);
////				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results99, 99, endDateStr);
////				}
//
//






// 月线反转5.0
//                System.out.println("monitor4ASC815_22_998 and date="+endDateStr);
//				List<stockCode> Results998 = monitor4ASC815_22_998.getFinal(endDateStr);
//				if(nowStr.equals(endDateStr)){
//					sendEmailWithDate(Results998, 998, endDateStr);
//				}

//		    	 14天死叉，第15天金叉
//				System.out.println("monitor4ASC815_22_92 and date="+endDateStr);
//				List<stockCode> Results92 = monitor4ASC815_22_92.getFinal(endDateStr);
//				sendEmailWithDate(Results92, 92, endDateStr);

				// rsi_6 from 20 to 45
//				System.out.println("monitor4ASC815_22_90 and date="+endDateStr);
//				List<stockCode> Results90 = monitor4ASC815_22_90.getFinal(endDateStr);
//				sendEmailWithDate(Results90, 90, endDateStr);


//				System.out.println("monitor4ASC815_22_91 and date="+endDateStr);
//				List<stockCode> Results91 = monitor4ASC815_22_91.getFinal(endDateStr);
//				sendEmailWithDate(Results91, 91, endDateStr);


//				System.out.println("monitor4ASC815_22_8888 and date="+endDateStr);
//				List<stockCode> Results8888 = monitor4ASC815_22_8888.getFinal(endDateStr);
//				sendEmailWithDate(Results8888, 8888, endDateStr);



//				System.out.println("monitor4ASC815_22_89 and date="+endDateStr);
//				List<stockCode> Results89 = monitor4ASC815_22_89.getFinal(endDateStr);
//				sendEmailWithDate(Results89, 89, endDateStr);


//
//				System.out.println("monitor4ASC815_22_82 and date="+endDateStr);
//				List<stockCode> Results82 = monitor4ASC815_22_82.getFinal(endDateStr,1,5);
//				sendEmailWithDate(Results82, 82, endDateStr);
//
//
////				System.out.println("monitor4ASC815_22_85 and date="+endDateStr);
////				List<stockCode> Results85 = monitor4ASC815_22_85.getFinal(endDateStr,1,5);
////				sendEmailWithDate(Results85, 85, endDateStr);
//
//
//				System.out.println("monitor4ASC815_22_72 and date="+endDateStr);
//				List<stockCode> Results72 = monitor4ASC815_22_72.getFinal(endDateStr);
//				sendEmail(Results72, 72, endDateStr);
//
//				System.out.println("monitor4ASC815_22_72_82 and date="+endDateStr);
//				List<stockCode> Results72_82 = monitor4ASC815_22_72_82.getFinal(endDateStr);
//				sendEmail(Results72_82, 7282, endDateStr);
//
//				System.out.println("monitor4ASC815_22_81 and date="+endDateStr);
//				List<stockCode> Results81 = monitor4ASC815_22_81.getFinal(endDateStr,1,5);
//				sendEmailWithDate(Results81, 81, endDateStr);
//
//				System.out.println("monitor4ASC815_22_80 and date="+endDateStr);
//				List<stockCode> Results80 = monitor4ASC815_22_80.getFinal(endDateStr,1,5);
//				sendEmailWithDate(Results80, 80, endDateStr);
//
//				System.out.println("monitor4ASC815_22_79 and date="+endDateStr);
//				List<stockCode> Results79 = monitor4ASC815_22_79.getFinal(endDateStr,1,5);
//				sendEmailWithDate(Results79, 79, endDateStr);
//
//				System.out.println("monitor4ASC815_22_78 and date="+endDateStr);
//				List<stockCode> Results78 = monitor4ASC815_22_78.getFinal(endDateStr,1,5);
//				sendEmailWithDate(Results78, 78, endDateStr);
//
//				System.out.println("monitor4ASC815_22_75 and date="+endDateStr);
//				List<stockCode> Results75 = monitor4ASC815_22_75.getFinal(endDateStr,1,5);
//				sendEmailWithDate(Results75, 75, endDateStr);
//
//		    	System.out.println("monitor4ASC815_22_77 and date="+endDateStr);
//		    	List<stockCode> Results77 = monitor4ASC815_22_77.getFinal(endDateStr,1,5);
//		    	sendEmailWithDate(Results77, 77, endDateStr);
//
//
//
//		    	System.out.println("monitor4ASC815_22_72_72  and date="+endDateStr);
//		    	List<stockCode> Results7272 = monitor4ASC815_22_72_72.getFinal(endDateStr);
//		    	sendEmailWithDate(Results7272, 7272, endDateStr);
//
//
//		    	System.out.println("monitor4ASC815_22_64 and date="+endDateStr);
//		    	List<stockCode> Results64 = monitor4ASC815_22_64.getFinal(endDateStr);
//		    	sendEmail(Results64, 64,endDateStr);
//
//		    	System.out.println("monitor4ASC815_22_72_72_72 and date="+endDateStr);
//		    	List<stockCode> Results7272_72 = monitor4ASC815_22_72_72_72.getFinal(endDateStr);
//		    	sendEmailWithDate(Results7272_72, 727272, endDateStr);
//
////		    	System.out.println("monitor4ASC815_22_76");
////		    	List<stockCode> Results76 = monitor4ASC815_22_76.getFinal(endDateStr,1,5);
////		    	sendEmailWithDate(Results76, 76, endDateStr);
//
//
//		    	System.out.println("monitor4ASC815_22_70 and date="+endDateStr);
//		    	List<stockCode> Results70 = monitor4ASC815_22_70.getFinal(endDateStr);
//		    	sendEmailWithDate(Results70, 70, endDateStr);
//
//		    	System.out.println("monitor4ASC815_22_73");
//		    	List<stockCode> Results73 = monitor4ASC815_22_73.getFinal(endDateStr);
//		    	sendEmailWithDate(Results73, 73, endDateStr);
//
//		    	System.out.println("monitor4ASC815_22_32");
//		    	List<stockCode> Results32 = monitor4ASC815_22_32.getFinal(endDateStr);
//		    	sendEmail(Results32, 32,endDateStr);
//		    	monitor4ASC815_22_33_ma30_ma60.getFinal(endDateStr);
//		    	System.out.println("monitor4ASC815_22_33");
//		    	List<stockCode> Results33 = monitor4ASC815_22_33.getFinal(endDateStr);
//			    sendEmail(Results33, 33,endDateStr);
//			    System.out.println("monitor4ASC815_22_34");
//			    List<stockCode> Results34 = monitor4ASC815_22_34.getFinal(endDateStr);
//			    sendEmail(Results34, 34,endDateStr);
//			    System.out.println("monitor4ASC815_22_35");
//			    List<stockCode> Results35 = monitor4ASC815_22_35.getFinal(endDateStr);
//			    sendEmail(Results35, 35,endDateStr);
//			    System.out.println("monitor4ASC815_22_36");
//			    List<stockCode> Results36 = monitor4ASC815_22_36.getFinal(endDateStr);
//			    sendEmail(Results36, 36,endDateStr);
//			    System.out.println("monitor4ASC815_22_38");
//			    List<stockCode> Results38 = monitor4ASC815_22_38.getFinal(endDateStr);
//			    sendEmail(Results38, 38,endDateStr);
//		    	System.out.println("monitor4ASC815_22_40");
//		    	List<stockCode> Results40 = monitor4ASC815_22_40.getFinal(endDateStr);
//		    	sendEmail(Results40, 40,endDateStr);
//		    	System.out.println("monitor4ASC815_22_41");
//		    	List<stockCode> Results41 = monitor4ASC815_22_41.getFinal(endDateStr);
//		    	sendEmail(Results41, 41,endDateStr);
//		    	System.out.println("monitor4ASC815_22_42");
//		    	List<stockCode> Results42 = monitor4ASC815_22_42.getFinal(endDateStr);
//		    	sendEmail(Results42, 42,endDateStr);
//		    	System.out.println("monitor4ASC815_22_43");
//		    	List<stockCode> Results43 = monitor4ASC815_22_43.getFinal(endDateStr);
//		    	sendEmail(Results43, 43,endDateStr);
//		    	System.out.println("monitor4ASC815_22_44");
//		    	List<stockCode> Results44 = monitor4ASC815_22_44.getFinal(endDateStr);
//		    	sendEmail(Results44, 44,endDateStr);
//		    	System.out.println("monitor4ASC815_22_45");
//		    	List<stockCode> Results45 = monitor4ASC815_22_45.getFinal(endDateStr);
//		    	sendEmail(Results45, 45,endDateStr);
//		    	System.out.println("monitor4ASC815_22_46");
//		    	List<stockCode> Results46 = monitor4ASC815_22_46.getFinal(endDateStr);
//		    	sendEmail(Results46, 46,endDateStr);
//		    	System.out.println("monitor4ASC815_22_47");
//		    	List<stockCode> Results47 = monitor4ASC815_22_47.getFinal(endDateStr);
//		    	sendEmail(Results47, 47,endDateStr);
//		    	System.out.println("monitor4ASC815_22_48");
//		    	List<stockCode> Results48 = monitor4ASC815_22_48.getFinal(endDateStr);
//		    	sendEmail(Results48, 48,endDateStr);
//		    	System.out.println("monitor4ASC815_22_49");
//		    	List<stockCode> Results49 = monitor4ASC815_22_49.getFinal(endDateStr);
//		    	sendEmail(Results49, 49,endDateStr);
//		    	System.out.println("monitor4ASC815_22_50");
//		    	List<stockCode> Results50 = monitor4ASC815_22_50.getFinal(endDateStr);
//		    	sendEmail(Results50, 50,endDateStr);
//		    	System.out.println("monitor4ASC815_22_51");
//		    	List<stockCode> Results51 = monitor4ASC815_22_51.getFinal(endDateStr);
//		    	sendEmail(Results51, 51,endDateStr);
//		    	System.out.println("monitor4ASC815_22_52");
//		    	List<stockCode> Results52 = monitor4ASC815_22_52.getFinal(endDateStr);
//		    	sendEmail(Results52, 52,endDateStr);
//		    	System.out.println("monitor4ASC815_22_53");
//		    	List<stockCode> Results53 = monitor4ASC815_22_53.getFinal(endDateStr);
//		    	sendEmail(Results53, 53,endDateStr);
//		    	System.out.println("monitor4ASC815_22_59");
//		    	List<stockCode> Results59 = monitor4ASC815_22_59.getFinal(endDateStr);
//		    	sendEmail(Results59, 59,endDateStr);
//		    	System.out.println("monitor4ASC815_22_60");
//		    	List<stockCode> Results60 = monitor4ASC815_22_60.getFinal(endDateStr);
//		    	sendEmail(Results60, 60,endDateStr);
//		    	System.out.println("monitor4ASC815_22_61");
//		    	List<stockCode> Results61 = monitor4ASC815_22_61.getFinal(endDateStr);
//		    	sendEmail(Results61, 61,endDateStr);
//		    	System.out.println("monitor4ASC815_22_62");
//		    	List<stockCode> Results62 = monitor4ASC815_22_62.getFinal(endDateStr);
//		    	sendEmail(Results62, 62,endDateStr);
//		    	System.out.println("monitor4ASC815_22_63");
//		    	List<stockCode> Results63 =monitor4ASC815_22_63.getFinal(endDateStr);
//		    	sendEmail(Results63, 63,endDateStr);
//		    	System.out.println("monitor4ASC815_22_65");
//		    	List<stockCode> Results65 = monitor4ASC815_22_65.getFinal(endDateStr);
//		    	sendEmailWithDate(Results65, 65, endDateStr);
//		    	System.out.println("monitor4ASC815_22_66");
//		    	List<stockCode> Results66 = monitor4ASC815_22_66.getFinal(endDateStr);
//		    	sendEmailWithDate(Results66, 66, endDateStr);
		    	
		    	
		    	
		    	
//		    	System.out.println("monitor4ASC815_22_71");
//		    	List<stockCode> Results71 = monitor4ASC815_22_71.getFinal(endDateStr);
//		    	sendEmailWithDate(Results71, 71, endDateStr);
//		    	System.out.println("monitor4ASC815_22_67");
//		    	List<stockCode> Results67 = monitor4ASC815_22_67.getFinal(endDateStr);
//		    	sendEmailWithDate(Results67, 67, endDateStr);
		    	
		    	
//		    	System.out.println("monitor4ASC815_22_68");
//		    	List<stockCode> Results68 = monitor4ASC815_22_68.getFinal(endDateStr);
//		    	sendEmailWithDate(Results68, 68, endDateStr);
		    	
//		    	monitor4ASC815_22_67_verify.getFinal(endDateStr);
		    	
//		    	monitor4ASC815_22_69.getFinal(endDateStr);
//		    	break;
		    	

		    	
		    	
//		    	System.out.println("monitor4ASC815_22_RiskLevel");
//		    	List<String> risks = monitor4ASC815_22_RiskLevel.getFinal(endDateStr);
//		    	String allRisk = "";
//		    	for(String risk: risks){
//		    		System.out.println(risk);
//		    		allRisk = allRisk + risk + "<br>";
//		    	}
//		    	sendEmail_0815_22_32(allRisk,99);
		    	
		    	
//		    	System.out.println("monitor4ASC815_22_32_IN");
//		    	monitor4ASC815_22_32_IN.getFinal(endDateStr);
		    	
			    
			    
		    	endDateStr = helper.getNextWorkDay(endDateStr);
//				nowStr = endDateStr;
		    	Date date = format.parse(endDateStr);

				Date date2 = format.parse("20221216");
				Date now  = new Date();
		    	
				if(date.getTime()>=now.getTime()){
					break;
				}
		    }
				
			
//			LoggingUtils.log(Level.INFO, logger, "senderTest. start.", null);
//			sender.sendEmail4Test();
			
//			while(true){
//				 helper.getStartDateStr20DaysBefore(endDateStr);
//
//				 System.out.println("========");
//
//				 endDateStr = helper.getNextWorkDay(endDateStr);
//
//
//				 Date date = format.parse(endDateStr);
//				 Date now  = new Date();
//				 if(date.getTime()>=now.getTime()){
//					break;
//				}
//			}
		   
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	

	
	private void sendEmail(List<StockCode> Results, int strategy, String endDateStr ) throws Exception{
		
		StringBuffer returnStr = new StringBuffer();
		
		if(Results!=null && Results.size()>0){
			returnStr.append("    strategy= "+strategy+" <br>");
			for (StockCode result : Results) {
				returnStr.append("Code:"+result.getCODE()+"  name:"+result.getNAME()+ "   PE:"+result.getPE()+"   MFRATIO2:"+result.getMFRATIO2()+ "    PROFIT:"+result.getPROFIT()+ "    lowest days:"+result.getSYMBOL()+" <br>");
			}
		}else
		{
//			LoggingUtils.log(Level.INFO, logger, "There is no 888 today. ", null);
			returnStr.append("");
		}
		
		sendEmail_0815_22_32(returnStr.toString(),strategy,endDateStr);
		
	}
	
	
    private void sendEmailWithDate(List<StockCode> Results, int strategy , String endDateStr) throws Exception{
		
		StringBuffer returnStr = new StringBuffer();
		
		if(Results!=null && Results.size()>0){
			returnStr.append(endDateStr + "    strategy= "+strategy+" <br>");
			for (StockCode result : Results) {
				returnStr.append("Code:"+result.getCODE()+"  name:"+result.getNAME()+ "   PE:"+result.getPE()+"   rev:"+result.getMFRATIO2()+ "    profit:"+result.getPROFIT()+ "    pb:"+result.getVOLUME()+" <br>");
			}
		}else
		{
//			LoggingUtils.log(Level.INFO, logger, "There is no 888 today. ", null);
			returnStr.append("");
		}
		
		sendEmail_0815_22_32(returnStr.toString(),strategy,endDateStr);
		
	}
	
	public void sendEmail_0815_22_32(String emailContent, int strategy,String endDateStr) throws Exception {
		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd");
		Date date = new Date();
		Session mailSession = null;
		try{
			
//			emailContent = getEmailContent();
//			emailContent = getEmail888Content_0815_22_32();
//			emailContent = "  xx ";
			if(emailContent!=null && !emailContent.equals("")){
				// 创建Properties 类用于记录邮箱的一些属性
		        final Properties props = new Properties();
		        // 表示SMTP发送邮件，必须进行身份验证
		        props.put("mail.smtp.auth", "true");
		        //此处填写SMTP服务器
//		        props.put("mail.smtp.host", "mail.migu.cn");
		        props.put("mail.smtp.host", "smtp.126.com");
		        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
//		        props.put("mail.smtp.port", "25");
		        // 此处填写你的账号
		        
		        props.put("mail.user", "doushubao1984@126.com");
		        // 此处的密码就是前面说的16位STMP口令
//		        props.put("mail.password", "ENAZADARGSHUYQXX");
				props.put("mail.password", "RJRXHTIYHPLAOCUG");

		        
//		        props.put("mail.user", "stmonitor888@126.com");
//		        // 此处的密码就是前面说的16位STMP口令
//		        props.put("mail.password", "888_888");
		        // 构建授权信息，用于进行SMTP进行身份验证
		        Authenticator authenticator = new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                // 用户名、密码
		                String userName = props.getProperty("mail.user");
		                String password = props.getProperty("mail.password");
		                return new PasswordAuthentication(userName, password);
		            }
		        };
		        // 使用环境属性和授权信息，创建邮件会话
		        mailSession = Session.getInstance(props, authenticator);
		        // 创建邮件消息
		        MimeMessage message = new MimeMessage(mailSession);
		        // 设置发件人
		        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
		        message.setFrom(form);
		        // 设置收件人的邮箱
		        if( strategy==82 || strategy==80 || strategy==79 || strategy==78 || strategy==77 || strategy==70 ||strategy==727272 || strategy==7272 || strategy==72 || strategy==62 || strategy==36 || strategy==35 || strategy==67 || strategy==99){
		        	InternetAddress[] sendTo = new InternetAddress[2];
			        sendTo[0] = new InternetAddress("doushubao1984@126.com");
			        sendTo[1] = new InternetAddress("brilliantcf@126.com");
//			        sendTo[2] = new InternetAddress("ysky_y@126.com");
//			        sendTo[3] = new InternetAddress("liumm0609@126.com");
//			        sendTo[4] = new InternetAddress("askashen@126.com");
			        
//			        sendTo[2] = new InternetAddress("ckduke2000@163.com");
//			        sendTo[3] = new InternetAddress("ysky_y@126.com");
			        
			        message.setRecipients(Message.RecipientType.TO, sendTo);
			        // 设置邮件标题
			        message.setSubject("ST strategy="+strategy + " date="+endDateStr);
			        // 设置邮件的内容体
			        message.setContent(emailContent, "text/html;charset=UTF-8");
			        // 最后当然就是发送邮件啦
			        Transport.send(message);
		        }else if(strategy==7282){
		        	InternetAddress[] sendTo = new InternetAddress[1];
			        sendTo[0] = new InternetAddress("doushubao1984@126.com");
//			        sendTo[1] = new InternetAddress("brilliantcf@126.com");
//					sendTo[2] = new InternetAddress("shizhiyou1990@126.com");
//					sendTo[3] = new InternetAddress("ntang2006@163.com");
//			        sendTo[2] = new InternetAddress("ckduke2000@163.com");
//			        sendTo[1] = new InternetAddress("ysky_y@126.com");
			        
			        message.setRecipients(Message.RecipientType.TO, sendTo);
			        // 设置邮件标题
			        message.setSubject("ST strategy="+strategy+ " date="+endDateStr);
			        // 设置邮件的内容体
			        message.setContent(emailContent, "text/html;charset=UTF-8");
			        // 最后当然就是发送邮件啦
			        Transport.send(message);
		        }else  if( strategy==881 || strategy==88781){
					InternetAddress[] sendTo = new InternetAddress[6];
					sendTo[0] = new InternetAddress("doushubao1984@126.com");
					sendTo[1] = new InternetAddress("brilliantcf@126.com");
					sendTo[2] = new InternetAddress("273162063@qq.com");
					sendTo[3] = new InternetAddress("clonalman@hotmail.com"); // 高德建
					sendTo[4] = new InternetAddress("758578141@qq.com"); // 姐夫
					sendTo[5] = new InternetAddress("chenpengnnn@sina.com"); // 陈鹏

//
//                    sendTo[3] = new InternetAddress("976713114@qq.com");

//					sendTo[5] = new InternetAddress("ntang2006@163.com");
//					sendTo[6] = new InternetAddress("yueyouwei@163.com");
//


//					sendTo[1] = new InternetAddress("brilliantcf@126.com");
//					sendTo[2] = new InternetAddress("1290061692@qq.com");
//					sendTo[2] = new InternetAddress("shizhiyou1990@126.com");
//					sendTo[3] = new InternetAddress("ntang2006@163.com");
//					sendTo[3] = new InternetAddress("125399365@qq.com");
//					sendTo[5] = new InternetAddress("188688681@qq.com");



//			        sendTo[2] = new InternetAddress("ckduke2000@163.com");
//			        sendTo[1] = new InternetAddress("ysky_y@126.com");

					message.setRecipients(Message.RecipientType.TO, sendTo);
					// 设置邮件标题
					message.setSubject("ST strategy="+strategy+ " date="+endDateStr);
					// 设置邮件的内容体
					message.setContent(emailContent, "text/html;charset=UTF-8");
					// 最后当然就是发送邮件啦
					Transport.send(message);
				}else {
					InternetAddress[] sendTo = new InternetAddress[4];
					sendTo[0] = new InternetAddress("doushubao1984@126.com");
					sendTo[1] = new InternetAddress("brilliantcf@126.com");
//					sendTo[2] = new InternetAddress("273162063@qq.com");
//					sendTo[3] = new InternetAddress("758578141@qq.com");
					sendTo[2] = new InternetAddress("clonalman@hotmail.com"); // 高德建
					sendTo[3] = new InternetAddress("chenpengnnn@sina.com"); // 陈鹏



//					sendTo[4] = new InternetAddress("clonalman@hotmail.com");

//					sendTo[3] = new InternetAddress("ntang2006@163.com");
//					sendTo[4] = new InternetAddress("yueyouwei@163.com");

//

//					sendTo[1] = new InternetAddress("brilliantcf@126.com");
//					sendTo[2] = new InternetAddress("1290061692@qq.com");
//					sendTo[2] = new InternetAddress("shizhiyou1990@126.com");
//					sendTo[3] = new InternetAddress("ntang2006@163.com");
//					sendTo[3] = new InternetAddress("125399365@qq.com");
//					sendTo[5] = new InternetAddress("188688681@qq.com");



//			        sendTo[2] = new InternetAddress("ckduke2000@163.com");
//			        sendTo[1] = new InternetAddress("ysky_y@126.com");

					message.setRecipients(Message.RecipientType.TO, sendTo);
					// 设置邮件标题
					message.setSubject("ST strategy="+strategy+ " date="+endDateStr);
					// 设置邮件的内容体
					message.setContent(emailContent, "text/html;charset=UTF-8");
					// 最后当然就是发送邮件啦
					Transport.send(message);
				}
		        
			}
		}catch (Exception e)
		{
//			throw new Exception(e.getMessage());
		}finally{
			mailSession = null;
		}
	}

	private void longTermIndexRisk(String nowStr, String endDateStr) throws Exception{
		// 上证指数
		boolean is0000001Index = this.isIndexData("000001.SH",endDateStr);
		// 深圳成指
		boolean is1399001Index = this.isIndexData("399001.SZ",endDateStr);
		// 创业板指
		boolean is1399006Index = this.isIndexData("399006.SZ",endDateStr);
		int riskIndex = 0;
		if(!is0000001Index || !is1399001Index || !is1399006Index){
			if(!is0000001Index){
				riskIndex = riskIndex +3;
			}
			if(!is1399001Index){
				riskIndex = riskIndex +3;
			}
			if(!is1399006Index){
				riskIndex = riskIndex +3;
			}
			if(nowStr.equals(endDateStr)){
				sendEmail_0815_22_32("<p>中长期系统性风险已经发生，系统风险预警指数为：<b>"+riskIndex+"</b></p>, <br><br> <p><b><font color=\"#FF0000\" size=\"10\">请减仓、轻仓、清仓！！！！！！！</font></b></p>", 111111, endDateStr);
			}
		} else {
			if(nowStr.equals(endDateStr)){
				sendEmail_0815_22_32("<p>未发生中长期系统性风险</p>, <br><br> <p><b><font color=\"#0000FF\" size=\"10\">请正常加减仓、正常持有。</font></b></p>", 111111, endDateStr);
			}
		}
	}

	private void shortTermIndexRisk(String nowStr, String endDateStr) throws Exception{
		// 上证指数
		boolean is0000001Index = this.isIndexData4Ma5("000001.SH",endDateStr);
		// 深圳成指
		boolean is1399001Index = this.isIndexData4Ma5("399001.SZ",endDateStr);
		// 创业板指
		boolean is1399006Index = this.isIndexData4Ma5("399006.SZ",endDateStr);
		int riskIndex = 0;
		if(!is0000001Index || !is1399001Index || !is1399006Index){
			if(!is0000001Index){
				riskIndex = riskIndex +3;
			}
			if(!is1399001Index){
				riskIndex = riskIndex +3;
			}
			if(!is1399006Index){
				riskIndex = riskIndex +3;
			}
			if(nowStr.equals(endDateStr)){
				sendEmail_0815_22_32("<p>短期期系统性风险已经发生，系统风险预警指数为：<b>"+riskIndex+"</b></p>, <br><br> <p><b><font color=\"#FF0000\" size=\"10\">请减仓、轻仓、清仓！！！！！！！</font></b></p>", 333333, endDateStr);
			}
		} else {
			if(nowStr.equals(endDateStr)){
				sendEmail_0815_22_32("<p>未发生短期系统性风险</p>, <br><br> <p><b><font color=\"#0000FF\" size=\"10\">请正常加减仓、正常持有。</font></b></p>", 333333, endDateStr);
			}
		}
	}

	private boolean isIndexData(String code, String endDateStr)throws Exception{
		boolean passIndex = false;
		String newStartDate = helper.getStartDateStr360DaysBefore(endDateStr); // 要当天

		List<IndexCodeData> indexCodeDataList  = indexCodeDataRepository.findIndexData(code, newStartDate, endDateStr);

		int days4His = 0;
		double previousDaysMa240 = 0.0;
		double previousDaysMa20 = 0.0;
		for(IndexCodeData indexCodeData:indexCodeDataList){

			boolean isNeedUpdate = false;

			String newEndDateStr = indexCodeData.getTrade_date();
//			String newEndDateStr = dateUtils.dateToStr(newEndDate);

			if(indexCodeData.getMa5()==0.0){
				String newStartDateStr = helper.getStartDateStr360DaysBefore(newEndDateStr); // 要当天
				List<IndexCodeData> newResultList2Days  = indexCodeDataRepository.findIndexData(code, newStartDateStr, newEndDateStr);

				if(indexCodeData.getMa5()==0.0){
					double day1MA30 = helper.computeMA30_MA60Index(newResultList2Days, 5);
					BigDecimal b60 = new BigDecimal(day1MA30);
					day1MA30 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa5(day1MA30);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa10()==0.0){
					double day1MA30 = helper.computeMA30_MA60Index(newResultList2Days, 10);
					BigDecimal b60 = new BigDecimal(day1MA30);
					day1MA30 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa10(day1MA30);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa20()==0.0){
					double day1MA30 = helper.computeMA30_MA60Index(newResultList2Days, 20);
					BigDecimal b60 = new BigDecimal(day1MA30);
					day1MA30 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa20(day1MA30);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa30()==0.0){
					double day1MA30 = helper.computeMA30_MA60Index(newResultList2Days, 30);
					BigDecimal b60 = new BigDecimal(day1MA30);
					day1MA30 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa30(day1MA30);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa60()==0.0){
					double day1MA60 = helper.computeMA30_MA60Index(newResultList2Days, 60);
					BigDecimal b60 = new BigDecimal(day1MA60);
					day1MA60 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa60(day1MA60);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa120()==0.0){
					double day1MA120 = helper.computeMA30_MA60Index(newResultList2Days, 120);
					BigDecimal b120 = new BigDecimal(day1MA120);
					day1MA120 = b120.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa120(day1MA120);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa240()==0.0){
					double day1MA250 = helper.computeMA30_MA60Index(newResultList2Days, 250);
					BigDecimal b250 = new BigDecimal(day1MA250);
					day1MA250 = b250.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa240(day1MA250);
					isNeedUpdate = true;
				}

				if(isNeedUpdate){
					indexCodeDataRepository.updateIndexDataMA120_MA240ById(indexCodeData);
				}
			}


			if(days4His==0){
				previousDaysMa20 = indexCodeData.getMa20();
			}else{
				if(previousDaysMa20>=indexCodeData.getMa20() ){
//					previousDaysMa20 = indexCodeData.getMa20();
					passIndex = true;
					break;
				}else{
					break;
				}
			}
			days4His++;
		}
		return passIndex;
	}

	private boolean isIndexData4Ma5(String code, String endDateStr)throws Exception{
		boolean passIndex = false;
		String newStartDate = helper.getStartDateStr360DaysBefore(endDateStr); // 要当天

		List<IndexCodeData> indexCodeDataList  = indexCodeDataRepository.findIndexData(code, newStartDate, endDateStr);

		int days4His = 0;
		double previousDaysMa240 = 0.0;
		double previousDaysMa5 = 0.0;
		for(IndexCodeData indexCodeData:indexCodeDataList){

			boolean isNeedUpdate = false;

			String newEndDateStr = indexCodeData.getTrade_date();
//			String newEndDateStr = dateUtils.dateToStr(newEndDate);

			if(indexCodeData.getMa5()==0.0){
				String newStartDateStr = helper.getStartDateStr360DaysBefore(newEndDateStr); // 要当天
				List<IndexCodeData> newResultList2Days  = indexCodeDataRepository.findIndexData(code, newStartDateStr, newEndDateStr);

				if(indexCodeData.getMa5()==0.0){
					double day1MA30 = helper.computeMA30_MA60Index(newResultList2Days, 5);
					BigDecimal b60 = new BigDecimal(day1MA30);
					day1MA30 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa5(day1MA30);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa10()==0.0){
					double day1MA30 = helper.computeMA30_MA60Index(newResultList2Days, 10);
					BigDecimal b60 = new BigDecimal(day1MA30);
					day1MA30 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa10(day1MA30);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa20()==0.0){
					double day1MA30 = helper.computeMA30_MA60Index(newResultList2Days, 20);
					BigDecimal b60 = new BigDecimal(day1MA30);
					day1MA30 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa20(day1MA30);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa30()==0.0){
					double day1MA30 = helper.computeMA30_MA60Index(newResultList2Days, 30);
					BigDecimal b60 = new BigDecimal(day1MA30);
					day1MA30 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa30(day1MA30);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa60()==0.0){
					double day1MA60 = helper.computeMA30_MA60Index(newResultList2Days, 60);
					BigDecimal b60 = new BigDecimal(day1MA60);
					day1MA60 = b60.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa60(day1MA60);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa120()==0.0){
					double day1MA120 = helper.computeMA30_MA60Index(newResultList2Days, 120);
					BigDecimal b120 = new BigDecimal(day1MA120);
					day1MA120 = b120.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa120(day1MA120);
					isNeedUpdate = true;
				}

				if(indexCodeData.getMa240()==0.0){
					double day1MA250 = helper.computeMA30_MA60Index(newResultList2Days, 250);
					BigDecimal b250 = new BigDecimal(day1MA250);
					day1MA250 = b250.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					indexCodeData.setMa240(day1MA250);
					isNeedUpdate = true;
				}

				if(isNeedUpdate){
					indexCodeDataRepository.updateIndexDataMA120_MA240ById(indexCodeData);
				}
			}


			if(days4His==0){
				previousDaysMa5 = indexCodeData.getMa5();
			}else{
				if(previousDaysMa5>=indexCodeData.getMa5() ){
//					previousDaysMa20 = indexCodeData.getMa20();
					passIndex = true;
					break;
				}else{
					break;
				}
			}
			days4His++;
		}
		return passIndex;
	}
	
	
}
