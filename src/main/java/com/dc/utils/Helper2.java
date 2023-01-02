package com.dc.utils;

import com.dc.dal.StockCodeRepository;
import com.dc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Helper2 {

	@Autowired
	private StockCodeRepository stockCodeRepository;



	public String getNowDate(){
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat( "yyyyMMdd");
		Calendar now = Calendar.getInstance();
		Date date = new Date();
		now.setTime(date);
		String dateStr = simpleDateFormat.format(now.getTime());
		return dateStr;
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


	public long dateCompare(String  startTime, String endTime, boolean isExternal) throws Exception {
		
		Date temp1 = null; 
		
		SimpleDateFormat datetempMiguPlus= new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat datetempInternal= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(isExternal)
		{
			temp1 = datetempMiguPlus.parse(startTime);
		}else{
			temp1 = datetempInternal.parse(startTime);
		}
        Date temp2 = datetempMiguPlus.parse(endTime); 

        long days = (temp2.getTime()-temp1.getTime())/1000/60/60/24;
         
		return days;
	}

	
	public  String getSign4AES(String data,String key) throws Exception{
		String sign = data;
		sign=AesEncryption.encrypt(sign, key);
		return sign;
	}
	public String buildGetUrl(String data,String url) throws Exception{
		data = data.replace("null", "");
		String sign = getSign4AES(data,"qfGrqkhIEloxzbkb");
		url = url + "?clientId=718240fb-b8b6-415c-a5e6-adfee5bf8910&signType=AES&sign="+sign;
		url = url + "&request="+URLEncoder.encode(data, "UTF-8");
		return url;
	}
	
	
	
	public String getStartDateStr(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd");
		Date date = null;
		 // 创建 Calendar 对象  
	    Calendar now = Calendar.getInstance();  
	    if(endDateStr!=null && !endDateStr.equals("")){
	    	// 指定一个日期  
	        date = format.parse(endDateStr);  
	        // 对 calendar 设置为 date 所定的日期  
	        now.setTime(date);  
	    }
		
        int days = 0;
        if(endDateStr.equals("2018-01-03")  || endDateStr.equals("2018-01-02") ){
        	days = days-3;
        }

		
		if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			days = days-3;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			days = days-4;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else{
			days = days-2;
			now.add(Calendar.DAY_OF_MONTH, days);
		}

		String dateStr = format.format(now.getTime());
		return dateStr;
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
//		System.out.println(dateStr);
		return dateStr;
	}

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
	
	
	public String getStartDateStr10DaysBefore(String endDateStr) throws ParseException
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
	    
	    
	    // 指定一个日期  
	    Date date101 = format.parse("2017-10-08");  
	    
    	// 指定一个日期  
        Date date111 = format.parse("2017-10-25");  
	    
	    int days = 0;
	    if(date.getTime()>=date101.getTime()  && date.getTime()<=date111.getTime()){
	    	days = days-7;
	    }
	    
		
		if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			days = days - 17;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			days = days - 18;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else{
			days = days - 16;
			now.add(Calendar.DAY_OF_MONTH, days);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		String dateStr = format.format(now.getTime());
		return dateStr;
	}
	
	public String getStartDateStr100DaysBefore(String endDateStr) throws ParseException
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
	    
	    
	    // 指定一个日期  
	    Date date101 = format.parse("20171008");
	    
    	// 指定一个日期  
        Date date111 = format.parse("20171025");
	    
	    int days = 0;
	    if(date.getTime()>=date101.getTime()  && date.getTime()<=date111.getTime()){
	    	days = days-7;
	    }
	    
		
    	if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			days = days - 117;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			days = days - 118;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else{
			days = days - 116;
			now.add(Calendar.DAY_OF_MONTH, days);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
		return dateStr;
	}
	
	
	
	public String getStartDateStr200DaysZhenBefore(String endDateStr) throws ParseException
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
	    
	    
	    // 指定一个日期  
	    Date date101 = format.parse("20171008");
	    
    	// 指定一个日期  
        Date date111 = format.parse("20171025");
	    
	    int days = 0;
	    if(date.getTime()>=date101.getTime()  && date.getTime()<=date111.getTime()){
	    	days = days-7;
	    }
	    
		
    	if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			days = days - 367;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			days = days - 368;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else{
			days = days - 366;
			now.add(Calendar.DAY_OF_MONTH, days);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
		return dateStr;
	}
	
	public String getStartDateStr200DaysBefore(String endDateStr) throws ParseException
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
	    
	  
	    int days = -200;
	    now.add(Calendar.DAY_OF_MONTH, days);
	    
		
//    	if(now.get(Calendar.DAY_OF_WEEK)==1)
//		{
//			days = days - 217;
//			now.add(Calendar.DAY_OF_MONTH, days);
//		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
//		{
//			days = days - 118;
//			now.add(Calendar.DAY_OF_MONTH, days);
//		}else{
//			days = days - 116;
//			now.add(Calendar.DAY_OF_MONTH, days);
//		}
		
		// for 10.1 holiday

		String dateStr = format.format(now.getTime());
		return dateStr;
	}

	public String getStartDateStr360DaysBefore(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyyMMdd");
		 // 创建 Calendar 对象  
	    Calendar now = Calendar.getInstance();

		Date date;
		if(endDateStr!=null && !endDateStr.equals("")){
			// 指定一个日期
			date = format.parse(endDateStr);
			// 对 calendar 设置为 date 所定的日期
			now.setTime(date);
		}
		int days = -460;
	    now.add(Calendar.DAY_OF_MONTH, days);
	    

		String dateStr = format.format(now.getTime());
		return dateStr;
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
	
	
	
	public String getStartDateStr50DaysAfter(String endDateStr) throws ParseException
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
	    
	    
	    // 指定一个日期  
//	    Date date101 = format.parse("2017-10-08");  
//	    
//    	// 指定一个日期  
//        Date date111 = format.parse("2017-10-25");  
	    
	    int days = 0;
//	    if(date.getTime()>=date101.getTime()  && date.getTime()<=date111.getTime()){
//	    	days = days+7;
//	    }
	    
		
		if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			days = days + 57;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			days = days + 58;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else{
			days = days + 56;
			now.add(Calendar.DAY_OF_MONTH, days);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
		return dateStr;
	}
	
	
	public String getStartDateStr20DaysBefore(String endDateStr) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd");
		 // 创建 Calendar 对象  
	    Calendar now = Calendar.getInstance();  
//	    if(endDateStr!=null && !endDateStr.equals("")){
//	    	// 指定一个日期  
	        Date date = format.parse(endDateStr);  
	        // 对 calendar 设置为 date 所定的日期  
	        now.setTime(date);  
//	    }
		
	    // 指定一个日期  
	    Date date101 = format.parse("2017-10-08");  
	    
    	// 指定一个日期  
        Date date111 = format.parse("2017-11-01");  
	    
	    int days = 0;
	    if(date.getTime()>=date101.getTime()  && date.getTime()<=date111.getTime()){
	    	days = days-7;
	    }
	    
	    
		if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			days = days-31;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 || now.get(Calendar.DAY_OF_WEEK)==3)
		{
			days = days-32;
			now.add(Calendar.DAY_OF_MONTH, days);
		}else{
			days = days-30;
			now.add(Calendar.DAY_OF_MONTH, days);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
		return dateStr;
	}
	
	
	public String getStartDateStr5DaysBeforeNow(String endDateStr) throws ParseException
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
		
		if(now.get(Calendar.DAY_OF_WEEK)==1)
		{
			now.add(Calendar.DAY_OF_MONTH, -6);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2 
				|| now.get(Calendar.DAY_OF_WEEK)==3
				|| now.get(Calendar.DAY_OF_WEEK)==4
				|| now.get(Calendar.DAY_OF_WEEK)==5
				|| now.get(Calendar.DAY_OF_WEEK)==6)
		{
			now.add(Calendar.DAY_OF_MONTH, -7);
		}else if(now.get(Calendar.DAY_OF_WEEK)==7) {
			now.add(Calendar.DAY_OF_MONTH, -5);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
		return dateStr;
	}
	
	public String getStartDateStr10DaysBeforeNow(String endDateStr) throws ParseException
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
		
		if(now.get(Calendar.DAY_OF_WEEK)==1   )
		{
			now.add(Calendar.DAY_OF_MONTH, -13);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2
				|| now.get(Calendar.DAY_OF_WEEK)==3
				|| now.get(Calendar.DAY_OF_WEEK)==4
				|| now.get(Calendar.DAY_OF_WEEK)==5
				|| now.get(Calendar.DAY_OF_WEEK)==6)
		{
			now.add(Calendar.DAY_OF_MONTH, -14);
		}else if(now.get(Calendar.DAY_OF_WEEK)==7) {
			now.add(Calendar.DAY_OF_MONTH, -12);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
		return dateStr;
	}
	
	public String getStartDateStr20DaysBeforeNow(String endDateStr) throws ParseException
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
		
		if(now.get(Calendar.DAY_OF_WEEK)==1   )
		{
			now.add(Calendar.DAY_OF_MONTH, -23);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2
				|| now.get(Calendar.DAY_OF_WEEK)==3
				|| now.get(Calendar.DAY_OF_WEEK)==4
				|| now.get(Calendar.DAY_OF_WEEK)==5
				|| now.get(Calendar.DAY_OF_WEEK)==6)
		{
			now.add(Calendar.DAY_OF_MONTH, -24);
		}else if(now.get(Calendar.DAY_OF_WEEK)==7) {
			now.add(Calendar.DAY_OF_MONTH, -22);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
//		System.out.println(dateStr);
		return dateStr;
	}
	
	public String getStartDateStr30DaysBeforeNow(String endDateStr) throws ParseException
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
		
		if(now.get(Calendar.DAY_OF_WEEK)==1   )
		{
			now.add(Calendar.DAY_OF_MONTH, -33);
		}else if(now.get(Calendar.DAY_OF_WEEK)==2
				|| now.get(Calendar.DAY_OF_WEEK)==3
				|| now.get(Calendar.DAY_OF_WEEK)==4
				|| now.get(Calendar.DAY_OF_WEEK)==5
				|| now.get(Calendar.DAY_OF_WEEK)==6)
		{
			now.add(Calendar.DAY_OF_MONTH, -34);
		}else if(now.get(Calendar.DAY_OF_WEEK)==7) {
			now.add(Calendar.DAY_OF_MONTH, -32);
		}
		
		// for 10.1 holiday
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
//		System.out.println(dateStr);
		return dateStr;
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
//		now.add(Calendar.DAY_OF_MONTH, -7);
		
		String dateStr = format.format(now.getTime());
//		System.out.println(dateStr);
		return dateStr;
	}

	public String getPreviouseWorkDay(String endDateStr) throws ParseException
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

//		if(now.get(Calendar.DAY_OF_WEEK)==1
//				|| now.get(Calendar.DAY_OF_WEEK)==2
//				|| now.get(Calendar.DAY_OF_WEEK)==3
//				|| now.get(Calendar.DAY_OF_WEEK)==4
//				|| now.get(Calendar.DAY_OF_WEEK)==5)
//		{
//			now.add(Calendar.DAY_OF_MONTH, -1);
//		}else if(now.get(Calendar.DAY_OF_WEEK)==6)
//		{
//			now.add(Calendar.DAY_OF_MONTH, -3);
//		}else if(now.get(Calendar.DAY_OF_WEEK)==7){
//			now.add(Calendar.DAY_OF_MONTH, -2);
//		}

		// for 10.1 holiday
		now.add(Calendar.DAY_OF_MONTH, -1);

		String dateStr = format.format(now.getTime());
//		System.out.println(dateStr);
		return dateStr;
	}
	
	
	public double computeMA(List<Stock> stockList)throws Exception
	{
		double maSUM =0.0;
		int i=0;
		for (Stock result : stockList) {
			if(result.getCLOSE()!=null && result.getCLOSE().indexOf(".")>0 && !result.getCLOSE().equals("0.0"))
			{
				maSUM = maSUM+  Double.parseDouble(result.getCLOSE());
				i++;
			}
			
		}
		
		return maSUM/i;
	}
	
	
	public double computeMA(List<HistorytradeInfo> stockList, int days, boolean is4Yesterday)throws Exception
	{
		double maSUM =0.0;
		int i=0;
		int k = 0;
		for (HistorytradeInfo result : stockList) {
			if(is4Yesterday && k==0){
				k++;
				continue;
			}
			
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


	public double computeMA30_MA60Index(List<IndexCodeData> stockList, int days)throws Exception
	{
		double maSUM =0.0;
		int i=0;
		int k = 0;
		for (IndexCodeData result : stockList) {

			if(i==days)
			{
				break;
			}
//			maSUM = maSUM+ result.getCLOSE();
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
	
	
	public double computeHighestPrice(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
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
	
	
	public double computeHighest4HightestPrice(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
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
	
	
	public double computeLowestPrice(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0){
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
	
	
	public double computeHighestHighPrice(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
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
	
	
	public double computeLowestLowPrice(List<HistorytradeInfo> stockList, int days)throws Exception
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
			if(hightPrivce>result.getLow())
			{
				hightPrivce = result.getClose();
			}
			i++;
		}
		
		return hightPrivce;
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
	
	
	public HistorytradeInfo computeLowestLowPrice4kdjWithDate(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		HistorytradeInfo historytradeInfo = null;
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0)
			{
				hightPrivce = result.getLow();
				historytradeInfo = result;
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce>result.getLow())
			{
				hightPrivce = result.getLow();
				historytradeInfo = result;
			}
			i++;
		}
		return historytradeInfo;
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

    public double getDaysBeforeClose(List<HistorytradeInfo> stockList, int days)throws Exception
    {
        double close = 0.0;
        int i=0;
        for (HistorytradeInfo result : stockList) {

            if(i==days)
            {
                close = result.getClose();
                break;
            }

            i++;
        }

        return close;
    }
	
	
	public double computeLowestMa5(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0){
				i++;
				continue;
			}
			if(i==1)
			{
				hightPrivce = result.getMa5();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce>result.getMa5())
			{
				hightPrivce = result.getMa5();
			}
			i++;
		}
		
		return hightPrivce;
	}
	
	
	public double computeHighestMa5(List<HistorytradeInfo> stockList, int days)throws Exception
	{
		double hightPrivce = 0.0;
		int i=0;
		for (HistorytradeInfo result : stockList) {
			if(i==0){
				i++;
				continue;
			}
			if(i==1)
			{
				hightPrivce = result.getMa5();
			}
			if(i==days)
			{
				break;
			}
			if(hightPrivce<result.getMa5())
			{
				hightPrivce = result.getMa5();
			}
			i++;
		}
		
		return hightPrivce;
	}


	public boolean getLowestVolumDays(List<Stkdj> queryResults, String endDateStr)throws Exception{
		int days = 30;
		double volume = 0.0;
		String date = "";
		int i=0;
		for (Stkdj result : queryResults) {
			if(i==0)
			{
				volume = result.getVol();
				date = result.getTrade_date();
			}
			if(i==days)
			{
				break;
			}
			if(volume>result.getVol())
			{
				volume = result.getVol();
				date = result.getTrade_date();
			}
			i++;
		}

		if(date.equalsIgnoreCase(endDateStr)){
			return  true;
		}else{
			return false;
		}

	}

	public boolean getHightestVolumDays(List<Stkdj> queryResults, String endDateStr)throws Exception{
		int days = 300;
		double volume = 0.0;
		double volume2 = 0.0;
		String date = "";
		boolean returnFlag =  false;
		int i=0;
		for (Stkdj result : queryResults) {
			if(i==0)
			{
				volume = result.getVol();
				date = result.getTrade_date();
			}
			if(i==1){
				volume2 = result.getVol();
			}
			if(i==days)
			{
				break;
			}
			if(volume<result.getVol())
			{
				volume = result.getVol();
				date = result.getTrade_date();
			}
			i++;
		}

		if(date.equalsIgnoreCase(endDateStr)  && (volume/volume2>=2)){
			returnFlag = true;
		}else{
			returnFlag = false;
		}

		return returnFlag;

	}


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

	//标准差
	public double getStandardDevition(int num,List<Double> list,double bollMid){
		double sum = 0;
		for(int i = 0;i < num;i++){
			sum += (list.get(i) -bollMid) * (list.get(i) -bollMid);
		}
		return Math.sqrt(sum/num);
	}

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
						if(Double.parseDouble(result.getMCAP())>10000000000.00)
						{
							Results.add(result);
						}
					}
				}else
				{
					continue;
				}

//				}
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


}
