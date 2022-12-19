package com.dc.dal;


import com.dc.model.Scpool;
import java.util.List;

public interface ScpoolRepositoryDAL {

	public List<Scpool> findOrderReqByName(String name) throws Exception;
	
	public List<Scpool> findOrderReqByCode(String code) throws Exception;
	
	public List<Scpool> findAllScpool() throws Exception;
	
	public List<Scpool> findAllScpoolByEndDateStr(String endDateStr) throws Exception;

	public List<Scpool> findAllScpoolByMonitor(String monitor) throws Exception;

	public List<Scpool> findAllScpoolByMonitorAndEndDateStr(String monitor, String endDateStr) throws Exception;

	public List<Scpool> findAllScpoolGteEndDateStr(String endDateStr,String nowDate,boolean is88,List<String> monitor) throws Exception;


	public List<Scpool> findAllScpoolGteEndDateStrAndSellAllOut(String endDateStr, String nowDate, boolean is88, List<String> monitor, int sellAllOut) throws Exception;

	public void updateStatusAndSellAllOutScpoolById(Scpool scpool)throws Exception;
}
