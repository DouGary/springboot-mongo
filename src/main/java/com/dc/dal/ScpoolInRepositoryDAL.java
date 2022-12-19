package com.dc.dal;

import com.dc.model.Scpool;
import com.dc.model.ScpoolIn;

import java.util.List;

public interface ScpoolInRepositoryDAL {

	public List<ScpoolIn> findOrderReqByName(String name) throws Exception;
	
	public List<ScpoolIn> findAllScpool() throws Exception;
	
	public void updateScpoolByCode(String code, String buyTime, String sellTime, int sellAmount, double sellPrice, double scPosition, double scReturn)throws Exception;

}
