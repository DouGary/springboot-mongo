package com.dc.dal;

import com.dc.model.DailyBasicPro;
import com.dc.model.Stockbasics;

import java.util.List;

public interface LongTouIndexProRepositoryDAL {

//	public List<scpool> findAllOrderReq() throws Exception ;
//

//	public List<stkdj> findOrderReqByName(String name) throws Exception;
//
//	public stkdj findSTKDJByCodeAndDate(String code, String date) throws Exception;

	public List<Stockbasics> findstockByRevAndProfit(double rev, double profit) throws Exception;
	public void updateCodeByName(String code, String name)throws Exception;

//	public List<scpool> findAllScpool() throws Exception;
//
//	public List<scpool> findAllScpoolByEndDateStr(String endDateStr) throws Exception;
//
	public List<DailyBasicPro> findOrderReqByStockcodeAndDate(String stockcode, String startDate, String endDate) throws Exception;
//
//	public List<stkdj> findStkdjReqByStockcodeAndDate(String stockcode, String date) throws Exception;

}
