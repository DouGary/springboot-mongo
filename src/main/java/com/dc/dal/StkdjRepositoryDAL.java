package com.dc.dal;


import com.dc.model.Scpool;
import com.dc.model.Stkdj;

import java.util.List;

public interface StkdjRepositoryDAL {


    public List<Stkdj> findOrderReqByName(String name) throws Exception;

    public Stkdj findSTKDJByCodeAndDate(String code, String date) throws Exception;

    public List<Stkdj> findSTKDJByCodeAndBeforeDate(String code, String date) throws Exception;

    List<Stkdj> findSTKDJByCodeAndBeforeDate(String code, String endDate, String startDate) throws Exception;

    public List<Scpool> findAllScpool() throws Exception;

    public List<Scpool> findAllScpoolByEndDateStr(String endDateStr) throws Exception;

    public List<Stkdj> findOrderReqByStockcodeAndDate(String stockcode, String date) throws Exception;

    public List<Stkdj> findStkdjReqByStockcodeAndDate(String stockcode, String date) throws Exception;

    public List<Stkdj> findSTKDJByCodeAndBeforeDateASC(String code, String endDate, String startDate) throws Exception;

}
