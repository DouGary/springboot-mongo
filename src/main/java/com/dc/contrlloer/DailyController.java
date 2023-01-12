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
	Monitor_999996 monitor_999996;

	@Autowired
	Monitor_9999931 monitor_9999931;

	@Autowired
	Monitor_999994 monitor_999994;

	@Autowired
	Monitor_99881 monitor_99881;

	@Autowired
	private Helper helper;

	Logger logger = LogManager.getLogger(DailyController.class);


//	@Scheduled(cron = "0 0 5 * * ?")
	@RequestMapping(value = "senderTest", method = RequestMethod.GET)
	public void senderTest() {
		try{
			String endDateStr="";
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			endDateStr=sdf.format(dt);
			boolean isSendEmailEveryDay = false;

//			String endDateStr = "20220909";
//			boolean isSendEmailEveryDay = true;

			SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");

			Date now1 = new Date();
			String nowStr = format.format(now1);

		    while(true){

				if(isSendEmailEveryDay){
					nowStr = endDateStr;
				}

				this.longTermIndexRisk(nowStr,endDateStr);
				this.shortTermIndexRisk(nowStr,endDateStr);

				// 突破 第一板
				System.out.println("monitor4ASC815_22_8888464 and date="+endDateStr);
				List<StockCode> Results8888464old = monitor4ASC815_22_8888464.getFinal(endDateStr);

				// 突破 第一板
				System.out.println("monitor4ASC815_22_88884641 and date="+endDateStr);
				List<StockCode> Results8888464 = monitor_88884641.getFinal(endDateStr);

				System.out.println("monitor4ASC815_22_8878 and date="+endDateStr);
				List<StockCode> Results8878 = monitor_8878.getFinal(endDateStr);

				//   只有量的约束，底部放量
				System.out.println("monitor4ASC815_22_88781 and date="+endDateStr);
				List<StockCode> Results88781 = monitor4ASC815_22_88781.getFinal(endDateStr);

				// vol/v_ma5>2
				System.out.println("monitor4ASC815_22_881 and date="+endDateStr);
				List<StockCode> Results881 = monitor4ASC815_22_881.getFinal(endDateStr);

				if(nowStr.equals(endDateStr)){
					sendEmailWithDate(Results88781, 88781, endDateStr);
					sendEmailWithDate(Results881, 881, endDateStr);
				}

				// 上升趋势
				System.out.println("monitor4A SC815_22_9999931 and date="+endDateStr);
				List<StockCode> Results9999931 = monitor_9999931.getFinal(endDateStr);

				endDateStr = helper.getNextWorkDay(endDateStr);
				Date date = format.parse(endDateStr);

				Date date2 = format.parse("20220909");
				Date now  = new Date();

				if(date.getTime()>=now.getTime()){
					break;
				}



				// 上升趋势
//				System.out.println("monitor4A SC815_22_999996 and date="+endDateStr);
//				List<StockCode> Results999996 = monitor_999996.getFinal(endDateStr);

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

//				// 上升趋势且回调到中轨附近
//				System.out.println("monitor4A SC815_22_999994 and date="+endDateStr);
//				List<StockCode> Results999994 = monitor_999994.getFinal(endDateStr);
//				// 监控881，boll.mid重新递增
//				System.out.println("monitor4A SC815_22_99881 and date="+endDateStr);
//				List<StockCode> Results99881 = monitor_99881.getFinal(endDateStr);



		    }

		}catch(Exception e){
			e.printStackTrace();
		}
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
			returnStr.append("");
		}

		sendEmail_0815_22_32(returnStr.toString(),strategy,endDateStr);

	}

	public void sendEmail_0815_22_32(String emailContent, int strategy,String endDateStr) throws Exception {
		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd");
		Date date = new Date();
		Session mailSession = null;
		try{

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
				props.put("mail.password", "RJRXHTIYHPLAOCUG");

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
	           if( strategy==881 || strategy==88781){
					InternetAddress[] sendTo = new InternetAddress[6];
					sendTo[0] = new InternetAddress("doushubao1984@126.com");
					sendTo[1] = new InternetAddress("brilliantcf@126.com");
					sendTo[2] = new InternetAddress("273162063@qq.com");
					sendTo[3] = new InternetAddress("clonalman@hotmail.com"); // 高德建
					sendTo[4] = new InternetAddress("758578141@qq.com"); // 姐夫
					sendTo[5] = new InternetAddress("chenpengnnn@sina.com"); // 陈鹏

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
					sendTo[2] = new InternetAddress("clonalman@hotmail.com"); // 高德建
					sendTo[3] = new InternetAddress("chenpengnnn@sina.com"); // 陈鹏

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
