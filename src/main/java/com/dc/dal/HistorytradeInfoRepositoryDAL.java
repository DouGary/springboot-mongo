package com.dc.dal;


import com.dc.model.HistorytradeInfo;

import java.util.List;

public interface HistorytradeInfoRepositoryDAL {

	public List<HistorytradeInfo> findHistorytradeInfo(String code, String startDate, String endDate)throws Exception;
	public List<HistorytradeInfo> findHistorytradeInfo2(String code, String startDate, String endDate)throws Exception;
	public List<HistorytradeInfo> findHistorytradeInfo3(String code, String startDate, String endDate) throws Exception;
	
	public List<HistorytradeInfo> findHistorytradeInfo1day(String code, String startDate, String endDate)throws Exception;
	
	public List<HistorytradeInfo> findHistorytradeInfoByDesc(String code, String startDate, String endDate)throws Exception;
	public List<HistorytradeInfo> findHistorytradeInfoByDesc4MA30_MA60(String code, String startDate, String endDate)throws Exception;

	public List<HistorytradeInfo> findHistorytradeInfoByDate(String startDate) throws Exception;
	public List<HistorytradeInfo> findHistorytradeInfoByDesc4MA30_MA60_ASC(String code, String startDate, String endDate) throws Exception;
	
	public void updateHistorytradeInfoById(HistorytradeInfo request)throws Exception;
	
	public void updateHistorytradeInfoKDJById(HistorytradeInfo request)throws Exception;
	
	public void updateHistorytradeInfoMA120_MA240ById(HistorytradeInfo request)throws Exception;

	public void updateHistorytradeInfoRPS50ById(HistorytradeInfo request)throws Exception;
	
}
