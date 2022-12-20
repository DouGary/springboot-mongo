package com.dc.utils;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.dc.dal.StockCodeRepository;
import com.dc.model.HistorytradeInfo;
import com.dc.model.IndexCodeData;
import com.dc.model.StockCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helper {
	
	@Autowired
	private StockCodeRepository stockCodeRepository;

	public String getStartDateStr70daysBefore(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");

		// 创建 Calendar 对象
		Calendar now = Calendar.getInstance();
		if(endDateStr!=null && !endDateStr.equals("")){
			// 指定一个日期
			Date date = format.parse(endDateStr);
			// 对 calendar 设置为 date 所定的日期
			now.setTime(date);
		}

//		System.out.println(now.get(Calendar.DAY_OF_WEEK));

		if(now.get(Calendar.DAY_OF_WEEK)==41)
		{
			now.add(Calendar.DAY_OF_MONTH, -73);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			now.add(Calendar.DAY_OF_MONTH, -74);
		}else{
			now.add(Calendar.DAY_OF_MONTH, -72);
		}

		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -67);

		String dateStr = format.format(now.getTime());
//		System.out.println(dateStr);
		return dateStr;
	}




	public double computeLowestClosePriceBefore1Day4kdj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0) {
				i++;
				continue;
			}
			if(i==1)
			{
				hightPrivce = result.getClose();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce>result.getClose())
			{
				hightPrivce = result.getClose();
			}
			i++;
		}

		return hightPrivce;
	}


	public double computeHighestClosePrice4kdj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0)
			{
				hightPrivce = result.getClose();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce<result.getClose())
			{
				hightPrivce = result.getClose();
			}
			i++;
		}

		return hightPrivce;
	}


	public HistorytradeInfo computeHighestHighVol4kdj4Obj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		HistorytradeInfo historytradeInfo = null;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0)
			{
				hightPrivce = result.getVol();
				historytradeInfo = result;
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce<result.getVol())
			{
				hightPrivce = result.getVol();
				historytradeInfo = result;
			}
			i++;
		}

		return historytradeInfo;
	}


	public HistorytradeInfo computeHighestHighPriceClose4kdj4Obj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		HistorytradeInfo historytradeInfo = null;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0)
			{
				hightPrivce = result.getClose();
				historytradeInfo = result;
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce<result.getClose())
			{
				hightPrivce = result.getClose();
				historytradeInfo = result;
			}
			i++;
		}

		return historytradeInfo;
	}


	public double computeHighestClosePriceBefore1Day4kdj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0) {
				i++;
				continue;
			}
			if(i==1)
			{
				hightPrivce = result.getClose();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce<result.getClose())
			{
				hightPrivce = result.getClose();
			}
			i++;
		}

		return hightPrivce;
	}


	public String getStartDateStr360DaysAfter(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");
		// 创建 Calendar 对象
		Calendar now = Calendar.getInstance();
//	    if(endDateStr!=null && !endDateStr.equals("")){
		// 指定一个日期
		Date date = format.parse(endDateStr);
		// 对 calendar 设置为 date 所定的日期
		now.setTime(date);
//	    }


		int days = 360;
		now.add(Calendar.DAY_OF_MONTH, days);

		String dateStr = format.format(now.getTime());
		return dateStr;
	}

	public List<String> findDaysStr(String begintTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dBegin = null;
		Date dEnd = null;
		try {
			dBegin = sdf.parse(begintTime);
			dEnd = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> daysStrList = new ArrayList<String>();
		daysStrList.add(sdf.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (dEnd.after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			String dayStr = sdf.format(calBegin.getTime());
			daysStrList.add(dayStr);
		}
		return daysStrList;
	}

	public String getStartDateStr60daysBefore(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");

		// 创建 Calendar 对象
		Calendar now = Calendar.getInstance();
		if(endDateStr!=null && !endDateStr.equals("")){
			// 指定一个日期
			Date date = format.parse(endDateStr);
			// 对 calendar 设置为 date 所定的日期
			now.setTime(date);
		}
		if(now.get(Calendar.DAY_OF_WEEK)==41)
		{
			now.add(Calendar.DAY_OF_MONTH, -73);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			now.add(Calendar.DAY_OF_MONTH, -74);
		}else{
			now.add(Calendar.DAY_OF_MONTH, -72);
		}

		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -67);
		String dateStr = format.format(now.getTime());
		return dateStr;
	}


	public String getStartDateStr15daysBefore(String endDateStr, int days) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");

		// 创建 Calendar 对象
		Calendar now = Calendar.getInstance();
		if(endDateStr!=null && !endDateStr.equals("")){
			// 指定一个日期
			Date date = format.parse(endDateStr);
			// 对 calendar 设置为 date 所定的日期
			now.setTime(date);
		}

		// for 10.1 holiday
		now.add(Calendar.DAY_OF_MONTH, days);

		String dateStr = format.format(now.getTime());
		return dateStr;
	}


	public String getStartDateStr360DaysBefore(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");
		 // 创建 Calendar 对象  
	    Calendar now = Calendar.getInstance();  
//	    if(endDateStr!=null && !endDateStr.equals("")){
	    	// 指定一个日期  
	        Date date = format.parse(endDateStr);  
	        // 对 calendar 设置为 date 所定的日期  
	        now.setTime(date);  
//	    }
	    
	  
	    int days = -460;
	    now.add(Calendar.DAY_OF_MONTH, days);
	    

		String dateStr = format.format(now.getTime());
		return dateStr;
	}

	
	
	public double computeMA30_MA60(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double maSUM =0.0;
		int i=0;
		int k = 0;
		for (HistorytradeInfo result : stockList) {
			if(i==days)
			{
				break;
			}
				maSUM = maSUM+ result.getClose();
				i++;
				k++;
		}
		return maSUM/i;
	}




	public double computeVMA30_MA60(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double maSUM =0.0;
		int i=0;
		int k = 0;
		for (HistorytradeInfo result : stockList) {

			if(i==days)
			{
				break;
			}
			maSUM = maSUM+ result.getVol();
			i++;
			k++;
		}
		return maSUM/i;
	}
	

	public double computeLowestClosePrice4kdj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0)
			{
				hightPrivce = result.getClose();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce>result.getClose())
			{
				hightPrivce = result.getClose();
			}
			i++;
		}

		return hightPrivce;
	}

	public double computeHighestHighPrice4kdj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0)
			{
				hightPrivce = result.getHigh();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce<result.getHigh())
			{
				hightPrivce = result.getHigh();
			}
			i++;
		}

		return hightPrivce;
	}


	/**
	 * Calculate EMA,
	 *
	 * @param list
	 *            :Price list to calculate，the first at head, the last at tail.
	 * @return
	 */
	public   Double getEXPMA(final List<Double> list, final int number) {
		// 开始计算EMA值，
		Double k = 2.0 / (number + 1.0);// 计算出序数
		Double ema = list.get(0);// 第一天ema等于当天收盘价
		for (int i = 1; i < list.size(); i++) {
			// 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
			ema = list.get(i) * k + ema * (1 - k);
		}
		return ema;
	}

	/**
	 * calculate MACD values
	 *
	 * @param list
	 *            :Price list to calculate，the first at head, the last at tail.
	 * @param shortPeriod
	 *            :the short period value.
	 * @param longPeriod
	 *            :the long period value.
	 * @param midPeriod
	 *            :the mid period value.
	 * @return
	 */
	public   HashMap<String, Double> getMACD(final List<Double> list, final int shortPeriod, final int longPeriod, int midPeriod) {
		HashMap<String, Double> macdData = new HashMap<String, Double>();
		List<Double> diffList = new ArrayList<Double>();
		Double shortEMA = 0.0;
		Double longEMA = 0.0;
		Double dif = 0.0;
		Double dea = 0.0;

		for (int i = list.size() - 1; i >= 0; i--) {
			List<Double> sublist = list.subList(0, list.size() - i);
			shortEMA = this.getEXPMA(sublist, shortPeriod);
			longEMA = this.getEXPMA(sublist, longPeriod);
			dif = shortEMA - longEMA;
			diffList.add(dif);
		}
		dea = this.getEXPMA(diffList, midPeriod);
		macdData.put("DIF", dif);
		macdData.put("DEA", dea);
		macdData.put("MACD", (dif - dea) * 2);
		return macdData;
	}


	//均值
	public double getAverage(int num,List<Double> list ){
		double sum = 0;
		for(int i = 0;i < num;i++){
			sum += list.get(i);
		}
		return (double)(sum / num);
	}

	// big
	public List<StockCode> secondStrategy() throws Exception
	{
		List<StockCode> Results = new ArrayList();
		try {
			List<StockCode> queryResults = stockCodeRepository.findAllOrderReq();

			for (StockCode result : queryResults) {
				if(result.getMFRATIO2()!=null && result.getMFRATIO2().indexOf(".")>0
						&& result.getMCAP()!=null && result.getMCAP().indexOf(".")>0
						&& result.getPE()!=null && result.getPE().indexOf(".")>0){
					if(Double.parseDouble(result.getMFRATIO2())>0)
					{
						// 大于200亿
						if(Double.parseDouble(result.getMCAP())>1000000000.00)
						{
							Results.add(result);
						}
					}
				}else
				{
					continue;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally
		{}
		return Results;
	}



	public List<StockCode> firstStrategy() throws Exception
	{
		List<StockCode> Results = new ArrayList();
		List<StockCode> queryResults = null;
		queryResults = stockCodeRepository.findAllOrderReq();
		return queryResults;
	}

	public double computeLowestLowPrice4kdj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0)
			{
				hightPrivce = result.getLow();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce>result.getLow())
			{
				hightPrivce = result.getLow();
			}
			i++;
		}

		return hightPrivce;
	}

	public HistorytradeInfo computeHighestHighPrice4kdj4Obj(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		HistorytradeInfo historytradeInfo = null;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0)
			{
				hightPrivce = result.getHigh();
				historytradeInfo = result;
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce<result.getHigh())
			{
				hightPrivce = result.getHigh();
				historytradeInfo = result;
			}
			i++;
		}

		return historytradeInfo;
	}


	public void sendEmail(String emailContent, int strategy,String endDateStr) throws Exception {
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
				}else  if( strategy==881 || strategy==88781 || strategy==9999931 ){
					InternetAddress[] sendTo = new InternetAddress[5];
					sendTo[0] = new InternetAddress("doushubao1984@126.com");
					sendTo[1] = new InternetAddress("brilliantcf@126.com");
					sendTo[2] = new InternetAddress("273162063@qq.com");
//					sendTo[3] = new InternetAddress("clonalman@hotmail.com"); // 高德建
					sendTo[3] = new InternetAddress("758578141@qq.com"); // 姐夫
					sendTo[4] = new InternetAddress("chenpengnnn@sina.com"); // 陈鹏
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
					InternetAddress[] sendTo = new InternetAddress[3];
					sendTo[0] = new InternetAddress("doushubao1984@126.com");
					sendTo[1] = new InternetAddress("brilliantcf@126.com");
					sendTo[2] = new InternetAddress("chenpengnnn@sina.com"); // 陈鹏
//					sendTo[2] = new InternetAddress("273162063@qq.com");
//					sendTo[3] = new InternetAddress("758578141@qq.com");
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


	public String get20DaysBefore(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");
		// 创建 Calendar 对象
		Calendar now = Calendar.getInstance();
		// 指定一个日期
		Date date = format.parse(endDateStr);
		// 对 calendar 设置为 date 所定的日期
		now.setTime(date);
		int days = -20;
		now.add(Calendar.DAY_OF_MONTH, days);
		String dateStr = format.format(now.getTime());
		return dateStr;
	}



	public  HashMap<String, Integer>  hashMapSort(Map<String, Integer> map){
		//1、按顺序保存map中的元素，使用LinkedList类型
		List<Map.Entry<String, Integer>> keyList = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		//2、按照自定义的规则排序
		Collections.sort(keyList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				if(o2.getValue().compareTo(o1.getValue())>0){
					return 1;
				}else if(o2.getValue().compareTo(o1.getValue())<0){
					return -1;
				}  else {
					return 0;
				}
			}

		});
		//3、将LinkedList按照排序好的结果，存入到HashMap中
		HashMap<String,Integer> result=new LinkedHashMap<>();
		for(Map.Entry<String, Integer> entry:keyList){
			result.put(entry.getKey(),entry.getValue());
		}
		return result;
	}


	public double computeHighestHighPrice4kdjNoFirstDay(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0 || i==1)
			{
				i++;
				continue;
			}
			if(i==2){
				hightPrivce = result.getHigh();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce<result.getHigh())
			{
				hightPrivce = result.getHigh();
			}
			i++;
		}

		return hightPrivce;
	}


	public String getNextWorkDay(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");
		// 创建 Calendar 对象
		Calendar now = Calendar.getInstance();
		if(endDateStr!=null && !endDateStr.equals("")){
			// 指定一个日期
			Date date = format.parse(endDateStr);
			// 对 calendar 设置为 date 所定的日期
			now.setTime(date);
		}

		if(now.get(Calendar.DAY_OF_WEEK)==1
				|| now.get(Calendar.DAY_OF_WEEK)==2
				|| now.get(Calendar.DAY_OF_WEEK)==3
				|| now.get(Calendar.DAY_OF_WEEK)==4
				|| now.get(Calendar.DAY_OF_WEEK)==5)
		{
			now.add(Calendar.DAY_OF_MONTH, +1);
		}else if(now.get(Calendar.DAY_OF_WEEK)==6)
		{
			now.add(Calendar.DAY_OF_MONTH, +3);
		}else if(now.get(Calendar.DAY_OF_WEEK)==7){
			now.add(Calendar.DAY_OF_MONTH, +2);
		}


		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, +7);

		String dateStr = format.format(now.getTime());
//		System.out.println(dateStr);
		return dateStr;
	}

	public double computeMA30_MA60Index(List<IndexCodeData> stockList, int days)throws Exception
	{
		double maSUM =0.0;
		int i=0;
		int k = 0;
		for (IndexCodeData result : stockList) {
//			if(k==0){
//				k++;
//				continue;
//			}

			if(i==days)
			{
				break;
			}
//			if(result()!=null && result.getCLOSE().indexOf(".")>0 && !result.getCLOSE().equals("0.0"))
//			{

			maSUM = maSUM+ result.getClose();
			i++;
			k++;
//			}

		}

//		if(i<days)
//		{
//			days = i;
//		}

		return maSUM/i;
	}

}
