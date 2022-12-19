package com.dc.dal;

import com.dc.model.Scpool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class ScpoolRepositoryImpl implements ScpoolRepositoryDAL {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Scpool> findOrderReqByName(String name) throws Exception {
	  List<Scpool> queryResults = new ArrayList<>();
      try {
			Query query = new Query();
			query.addCriteria(Criteria.where("NAME").is(name));
			queryResults = mongoTemplate.find(query, Scpool.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
	  return queryResults;
	}
	
	
	@Override
	public List<Scpool> findOrderReqByCode(String code) throws Exception {
	  List<Scpool> queryResults = new ArrayList<>();
      try {
			Query query = new Query();
			query.addCriteria(Criteria.where("code").is(code));
//			query.with(new Sort(new Order(Direction.DESC,"createTime")));
		  query.with(Sort.by(Sort.Order.desc("createTime")));
			queryResults = mongoTemplate.find(query, Scpool.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
	  return queryResults;
	}
	
	@Override
	public List<Scpool> findAllScpool() throws Exception  {
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
	public List<Scpool> findAllScpoolByEndDateStr(String endDateStr) throws Exception  {
	  List<Scpool> queryResults = new ArrayList<>();
      try {
			Query query = new Query();
			query.addCriteria(where("createTime").is(endDateStr));
			
			queryResults = mongoTemplate.find(query, Scpool.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
	  return queryResults;
	}

	@Override
	public List<Scpool> findAllScpoolByMonitor(String monitor) throws Exception  {
		List<Scpool> queryResults = new ArrayList<>();
		try {
			Query query = new Query();
			query.addCriteria(where("monitor").is(monitor));
//			query.with(new Sort(new Order(Direction.ASC,"createTime")));
			query.with(Sort.by(Sort.Order.asc("createTime")));
			queryResults = mongoTemplate.find(query, Scpool.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("queryResults.size():"+queryResults.size());
		return queryResults;
	}

	@Override
	public List<Scpool> findAllScpoolByMonitorAndEndDateStr(String monitor, String endDateStr) throws Exception{
		List<Scpool> queryResults = new ArrayList<>();
		try {
			Query query = new Query();
			query.addCriteria(where("monitor").is(monitor));
			query.addCriteria(where("createTime").is(endDateStr));

			queryResults = mongoTemplate.find(query, Scpool.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryResults;
	}

	@Override
	public List<Scpool> findAllScpoolGteEndDateStr(String startDateStr,String nowDate, boolean is88, List<String> monitor) throws Exception  {
		// TODO Auto-generated method stub
		List<Scpool> queryResults = new ArrayList<>();
		try {
			Query query = new Query();
			query.addCriteria(where("createTime").gte(startDateStr).andOperator(where("createTime").lte(nowDate)));
			if(is88){
				Criteria c = new Criteria("monitor").in(monitor);
				query.addCriteria(c);
			}

			queryResults = mongoTemplate.find(query, Scpool.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryResults;
	}

	@Override
	public void updateStatusAndSellAllOutScpoolById(Scpool scpool)throws Exception
	{
		try {
			Query query = new Query(where("_id").is(scpool.getId()));
			Update update = new Update();
			update.set("status", scpool.getStatus());
			update.set("sellAllOut", scpool.getSellAllOut());
			mongoTemplate.updateFirst(query, update, Scpool.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Scpool> findAllScpoolGteEndDateStrAndSellAllOut(String startDateStr, String nowDate, boolean is88, List<String> monitor, int sellAllOut) throws Exception {
		// TODO Auto-generated method stub
		List<Scpool> queryResults = new ArrayList<>();
		try {
			Query query = new Query();
			query.addCriteria(where("createTime").gte(startDateStr).andOperator(where("createTime").lte(nowDate)));
//			query.addCriteria(where("sellAllOut").lte(sellAllOut));
			if(is88){
				Criteria c = new Criteria("monitor").in(monitor);

//				Criteria c = where("monitor").is(monitor.get(0));
//				int i=0;
//				for (String s : monitor) {
//					if(i==0) {
//						i++;
//						continue;
//					}
//					c.inorOperator(where("monitor").is(s));
//
//				}
				query.addCriteria(c);
				query.with(Sort.by(Sort.Order.desc("createTime")));
			}

			queryResults = mongoTemplate.find(query, Scpool.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryResults;
	}
}
