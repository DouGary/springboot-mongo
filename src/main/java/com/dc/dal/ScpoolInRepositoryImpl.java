package com.dc.dal;

import com.dc.model.Scpool;
import com.dc.model.ScpoolIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class ScpoolInRepositoryImpl implements ScpoolInRepositoryDAL {
	@Autowired
	private MongoTemplate mongoTemplate;

	private static Logger logger = LogManager.getLogger(ScpoolInRepositoryImpl.class);
	@Override
	public List<ScpoolIn> findOrderReqByName(String name) throws Exception {
	  List<ScpoolIn> queryResults = new ArrayList<>();
      try {
			Query query = new Query();
			query.addCriteria(Criteria.where("NAME").is(name));
			queryResults = mongoTemplate.find(query, ScpoolIn.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
	  return queryResults;
	}
	
	@Override
	public List<ScpoolIn> findAllScpool() throws Exception  {
	  List<ScpoolIn> queryResults = new ArrayList<>();
      try {
			Query query = new Query();
			queryResults = mongoTemplate.find(query, ScpoolIn.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
	  return queryResults;
	}
	
	
	
	@Override
	public void updateScpoolByCode(String code, String buyTime ,String sellTime, int sellAmount,double sellPrice,  double scPosition, double scReturn)throws Exception
	{
        try {
			Query query = new Query();
			
			query.addCriteria(where("code").is(code));
        	query.addCriteria(where("buyTime").is(buyTime));
			Update update = new Update();
			update.set("sellTime", sellTime);
			update.set("sellAmount", sellAmount);
			update.set("sellPrice", sellPrice);
			update.set("scPosition", scPosition);
			update.set("scReturn", scReturn);
			mongoTemplate.updateMulti(query, update, ScpoolIn.class);
		 } catch (Exception e) {
             e.printStackTrace();
             throw e;
         }
	}




}
