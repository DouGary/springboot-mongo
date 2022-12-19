package com.dc.dal;


import com.dc.model.Scpool;
import com.dc.model.Stkdj;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class StkdjRepositoryImpl implements StkdjRepositoryDAL {
    @Autowired
    private MongoTemplate mongoTemplate;
    //
    private static Logger logger = LogManager.getLogger(StkdjRepositoryImpl.class);

    @Override
    public List<Stkdj> findOrderReqByName(String name) throws Exception {
        // TODO Auto-generated method stub
        List<Stkdj> queryResults = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("NAME").is(name));
            queryResults = mongoTemplate.find(query, Stkdj.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResults;
    }

    @Override
    public List<Stkdj> findOrderReqByStockcodeAndDate(String stockcode, String date) throws Exception {

        List<Stkdj> queryResults = new ArrayList<>();
        if (stockcode.indexOf("SZ") > 0 || stockcode.indexOf("SH") > 0) {

        } else {
            String codeStr = stockcode.substring(0, 3);
            if (codeStr.equals("000") || codeStr.equals("002") || codeStr.equals("300")) {
                stockcode = stockcode + ".SZ";
            } else {
                stockcode = stockcode + ".SH";
            }
        }
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("ts_code").is(stockcode));
            query.addCriteria(Criteria.where("trade_date").gte(date));
//			query.with(new Sort(new Order(Direction.ASC,"trade_date")));
            query.with(Sort.by(Sort.Order.asc("trade_date")));
            queryResults = mongoTemplate.find(query, Stkdj.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResults;
    }

    @Override
    public List<Stkdj> findStkdjReqByStockcodeAndDate(String stockcode, String date) throws Exception {

        List<Stkdj> queryResults = new ArrayList<>();
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("ts_code").is(stockcode));
            query.addCriteria(Criteria.where("trade_date").gt(date));
//			query.with(new Sort(new Order(Direction.ASC,"trade_date")));
            query.with(Sort.by(Sort.Order.asc("trade_date")));
            queryResults = mongoTemplate.find(query, Stkdj.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return queryResults;
    }

    @Override
    public List<Scpool> findAllScpool() {
        List<Scpool> queryResults = new ArrayList<>();
        try {
            Query query = new Query();
            queryResults = mongoTemplate.find(query, Scpool.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResults;
    }


    @Override
    public List<Scpool> findAllScpoolByEndDateStr(String endDateStr) {
        List<Scpool> queryResults = new ArrayList<>();
        try {
            Query query = new Query();
            query.addCriteria(where("trade_date").is(endDateStr));
            queryResults = mongoTemplate.find(query, Scpool.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResults;
    }

    @Override
    public Stkdj findSTKDJByCodeAndDate(String code, String date) throws Exception {
        Stkdj stkdj = null;
        try {
            Query query = new Query();

            query.addCriteria(where("ts_code").is(code));
            query.addCriteria(where("trade_date").is(date));
//        	query.with(new Sort(new Order(Direction.DESC,"trade_date")));
            query.with(Sort.by(Sort.Order.desc("trade_date")));
            List<Stkdj> queryResults = mongoTemplate.find(query, Stkdj.class);
            if (queryResults != null && queryResults.size() > 0) {
                for (Stkdj result : queryResults) {
                    stkdj = result;
                    break;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stkdj;
    }


    @Override
    public List<Stkdj> findSTKDJByCodeAndBeforeDate(String code, String date) throws Exception {
        List<Stkdj> queryResults = null;
        try {
            Query query = new Query();

            query.addCriteria(where("ts_code").is(code));
            query.addCriteria(where("trade_date").lte(date));
//        	query.with(new Sort(new Order(Direction.DESC,"trade_date")));
            query.with(Sort.by(Sort.Order.desc("trade_date")));
            queryResults = mongoTemplate.find(query, Stkdj.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResults;
    }

    @Override
    public List<Stkdj> findSTKDJByCodeAndBeforeDate(String code, String endDate, String startDate) throws Exception {
        List<Stkdj> queryResults = null;
        try {
            Query query = new Query();

            query.addCriteria(where("ts_code").is(code));
            query.addCriteria(where("trade_date").lte(endDate).andOperator(where("trade_date").gte(startDate)));
//            query.with(new Sort(new Order(Direction.DESC,"trade_date")));
            query.with(Sort.by(Sort.Order.desc("trade_date")));
            queryResults = mongoTemplate.find(query, Stkdj.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResults;
    }


    @Override
    public List<Stkdj> findSTKDJByCodeAndBeforeDateASC(String code, String startDate, String endDate) throws Exception {
        List<Stkdj> queryResults = null;
        try {
            Query query = new Query();

            query.addCriteria(where("ts_code").is(code));
            query.addCriteria(where("trade_date").lte(endDate).andOperator(where("trade_date").gte(startDate)));
//			query.addCriteria(where("trade_date").gte(startDate));
            query.with(Sort.by(Sort.Order.asc("trade_date")));

            queryResults = mongoTemplate.find(query, Stkdj.class);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return queryResults;
    }


}
