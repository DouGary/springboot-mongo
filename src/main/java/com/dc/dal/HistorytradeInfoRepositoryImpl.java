package com.dc.dal;


import com.dc.dal.HistorytradeInfoRepositoryDAL;
import com.dc.model.HistorytradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class HistorytradeInfoRepositoryImpl implements HistorytradeInfoRepositoryDAL {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<HistorytradeInfo> findHistorytradeInfo(String code, String startDate, String endDate) throws Exception {
		List<HistorytradeInfo> queryResults = new ArrayList<>();
        try {
			Query query = new Query();
			query.addCriteria(where("code").is(code));
			query.addCriteria(where("trade_date").gte(startDate).andOperator(where("trade_date").lt(endDate)));
//			query.with(new Sort(new Order(Direction.ASC,"trade_date")));
			query.with(Sort.by(Sort.Order.desc("trade_date")));

			//query.addCriteria(where("TIME").gte(format.parse("2017-06-01")));
			queryResults = mongoTemplate.find(query, HistorytradeInfo.class);
		 } catch (Exception e) {
             e.printStackTrace();
         }
		return queryResults;
	}
	
	@Override
	public List<HistorytradeInfo> findHistorytradeInfo2(String code, String startDate, String endDate) throws Exception {
		List<HistorytradeInfo> queryResults = new ArrayList<>();
        try {
			Query query = new Query();
			query.addCriteria(where("code").is(code));
			query.addCriteria(where("trade_date").gt(startDate).andOperator(where("trade_date").lt(endDate)));
//			query.with(new Sort(new Order(Direction.ASC,"trade_date")));
			query.with(Sort.by(Sort.Order.asc("trade_date")));
			//query.addCriteria(where("TIME").gte(format.parse("2017-06-01")));
			queryResults = mongoTemplate.find(query, HistorytradeInfo.class);
		 } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
		return queryResults;
	}
	
	@Override
	public List<HistorytradeInfo> findHistorytradeInfo3(String code, String startDate, String endDate) throws Exception {
		List<HistorytradeInfo> queryResults = new ArrayList<>();
        try {
			Query query = new Query();
			query.addCriteria(where("code").is(code));
			query.addCriteria(where("trade_date").gte(startDate).andOperator(where("trade_date").lte(endDate)));
//			query.with(new Sort(new Order(Direction.ASC,"trade_date")));
			//query.addCriteria(where("TIME").gte(format.parse("2017-06-01")));
			query.with(Sort.by(Sort.Order.asc("trade_date")));
			queryResults = mongoTemplate.find(query, HistorytradeInfo.class);
		 } catch (Exception e) {
             e.printStackTrace();
         }
		return queryResults;
	}
	
	@Override
	public List<HistorytradeInfo> findHistorytradeInfoByDesc(String code, String startDate, String endDate) throws Exception {
		List<HistorytradeInfo> queryResults= null;
        try {
			Query query = new Query();
			query.addCriteria(where("code").is(code));
			query.addCriteria(where("trade_date").gte(startDate).andOperator(where("trade_date").lt(endDate)));
//			query.with(new Sort(new Order(Direction.DESC,"trade_date")));
			query.with(Sort.by(Sort.Order.desc("trade_date")));
			queryResults = mongoTemplate.find(query, HistorytradeInfo.class);
		 } catch (Exception e) {
             e.printStackTrace();
         }
		return queryResults;
	}
	
	
	@Override
	public List<HistorytradeInfo> findHistorytradeInfoByDesc4MA30_MA60(String code, String startDate, String endDate) throws Exception {
		List<HistorytradeInfo> queryResults = null;
        try {
			Query query = new Query();
			query.addCriteria(where("code").is(code));
			query.addCriteria(where("trade_date").gte(startDate).andOperator(where("trade_date").lte(endDate)));//交易时间大于等于 并且小于等于
			query.with(Sort.by(Sort.Order.desc("trade_date")));
			queryResults = mongoTemplate.find(query, HistorytradeInfo.class);
		 } catch (Exception e) {
             e.printStackTrace();
         }
		return queryResults;
	}


	@Override
	public List<HistorytradeInfo> findHistorytradeInfoByDate(String startDate) throws Exception {
		List<HistorytradeInfo> queryResults = null;
		try {
			Query query = new Query();
			query.addCriteria(where("trade_date").is(startDate));
			query.with(Sort.by(Sort.Order.desc("vol")));
			query.limit(10);
			queryResults = mongoTemplate.find(query, HistorytradeInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryResults;
	}
	
	@Override
	public List<HistorytradeInfo> findHistorytradeInfoByDesc4MA30_MA60_ASC(String code, String startDate, String endDate) throws Exception {
		List<HistorytradeInfo> queryResults = new ArrayList<>();
        try {
			Query query = new Query();
			query.addCriteria(where("code").is(code));
			query.addCriteria(where("trade_date").gte(startDate).andOperator(where("trade_date").lte(endDate)));
//			query.with(new Sort(new Order(Direction.ASC,"trade_date")));
			query.with(Sort.by(Sort.Order.asc("trade_date")));
			queryResults = mongoTemplate.find(query, HistorytradeInfo.class);
		 } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
		return queryResults;

	}



	@Override
	public List<HistorytradeInfo> findHistorytradeInfo1day(String code, String startDate, String endDate)
			throws Exception {
		List<HistorytradeInfo> queryResults = new ArrayList<>();
        try {
			Query query = new Query();
			query.addCriteria(where("code").is(code));
			query.addCriteria(where("trade_date").gte(startDate).andOperator(where("trade_date").lte(endDate)));
//			query.with(new Sort(new Order(Direction.ASC,"trade_date")));
			query.with(Sort.by(Sort.Order.asc("trade_date")));
			queryResults = mongoTemplate.find(query, HistorytradeInfo.class);
		 } catch (Exception e) {
             e.printStackTrace();
         }
		return queryResults;
	}
	
	
	@Override
	public void updateHistorytradeInfoById(HistorytradeInfo request)throws Exception
	{
        try {
			Query query = new Query(where("_id").is(request.getId()));
			Update update = new Update();
			update.set("ma30", request.getMa30());
			update.set("ma60", request.getMa60());
			mongoTemplate.updateFirst(query, update, HistorytradeInfo.class);
		 } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             throw e;
         }
	}

	@Override
	public void updateHistorytradeInfoRPS50ById(HistorytradeInfo request)throws Exception
	{
		try {
			Query query = new Query(where("_id").is(request.getId()));
			Update update = new Update();
			update.set("rps50", request.getRps50());
			mongoTemplate.updateFirst(query, update, HistorytradeInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	
	
	@Override
	public void updateHistorytradeInfoKDJById(HistorytradeInfo request)throws Exception
	{
        try {
			Query query = new Query(where("_id").is(request.getId()));
			Update update = new Update();
			update.set("kdjk", request.getKdjk());
			update.set("kdjd", request.getKdjd());
			update.set("kdjj", request.getKdjj());
			mongoTemplate.updateFirst(query, update, HistorytradeInfo.class);
		 } catch (Exception e) {
             e.printStackTrace();
             throw e;
         }
	}
	
	
	public void updateHistorytradeInfoMA120_MA240ById(HistorytradeInfo request)throws Exception{
		try {
			Query query = new Query(where("_id").is(request.getId()));
			Update update = new Update();
			update.set("ma120", request.getMa120());
			update.set("ma240", request.getMa240());
			update.set("ma30", request.getMa30());
			update.set("ma60", request.getMa60());
			update.set("ma5",request.getMa5());
			update.set("ma10",request.getMa10());
			update.set("ma20",request.getMa20());
			update.set("v_ma5",request.getV_ma5());
			update.set("v_ma10",request.getV_ma10());
			update.set("v_ma20",request.getV_ma20());

			mongoTemplate.updateFirst(query, update, HistorytradeInfo.class);
		 } catch (Exception e) {
             e.printStackTrace();
             throw e;
         }
	}
	
	

}
