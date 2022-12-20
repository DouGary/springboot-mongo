package com.dc.dal;

import com.dc.model.DailyBasicPro;
import com.dc.model.Stockbasics;
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
public class LongTouIndexProRepositoryImpl implements LongTouIndexProRepositoryDAL {
	@Autowired
	private MongoTemplate mongoTemplate;
//
//	private static Logger logger = LogManager.getLogger(stockbasicsRepositoryImpl.class);
//	@Override
//	public List<stkdj> findOrderReqByName(String name) throws Exception {
//		// TODO Auto-generated method stub
//	  List<stkdj> queryResults = new ArrayList<>();
//      try {
//			Query query = new Query();
//			query.addCriteria(Criteria.where("NAME").is(name));
//			queryResults = mongoTemplate.find(query, stkdj.class);
//      } catch (Exception e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//	  return queryResults;
//	}



	@Override
	public void updateCodeByName(String code,String name)throws Exception
	{
		try {

			Query query = new Query(where("name").is(name));
			Update update = new Update();
			update.set("code", code);

			mongoTemplate.updateFirst(query, update, Stockbasics.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<DailyBasicPro> findOrderReqByStockcodeAndDate(String stockcode, String startDate, String endDate) throws Exception {
		List<DailyBasicPro> queryResults = null;
		try {
			Query query = new Query();

			query.addCriteria(where("ts_code").is(stockcode));
			query.addCriteria(where("trade_date").lte(endDate).andOperator(where("trade_date").gte(startDate)));
//			query.addCriteria(where("trade_date").gte(startDate));
			query.with(Sort.by(Sort.Order.desc("trade_date")));

			queryResults = mongoTemplate.find(query, DailyBasicPro.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryResults;
	}

	@Override
	public List<Stockbasics> findstockByRevAndProfit(double rev,double profit) throws Exception {
		// TODO Auto-generated method stub
		List<Stockbasics> queryResults = new ArrayList<>();
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("rev").gte(rev));
			query.addCriteria(Criteria.where("profit").gte(profit));
			query.with(Sort.by(Sort.Order.desc("profit")));
			queryResults = mongoTemplate.find(query, Stockbasics.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryResults;
	}
//
//	@Override
//	public List<stkdj> findStkdjReqByStockcodeAndDate(String stockcode,String date) throws Exception {
//		// TODO Auto-generated method stub
//		List<stkdj> queryResults = new ArrayList<>();
//		try {
//			Query query = new Query();
//			query.addCriteria(Criteria.where("stockcode").is(stockcode));
//			query.addCriteria(Criteria.where("datep").gt(date));
//			query.with(new Sort(new Order(Direction.ASC,"datep")));
//			queryResults = mongoTemplate.find(query, stkdj.class);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return queryResults;
//	}
//
//	@Override
//	public List<scpool> findAllScpool() throws Exception  {
//		// TODO Auto-generated method stub
//	  List<scpool> queryResults = new ArrayList<>();
//      try {
//			Query query = new Query();
//			queryResults = mongoTemplate.find(query, scpool.class);
//      } catch (Exception e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//	  return queryResults;
//	}
//
//
//	@Override
//	public List<scpool> findAllScpoolByEndDateStr(String endDateStr) throws Exception  {
//		// TODO Auto-generated method stub
//	  List<scpool> queryResults = new ArrayList<>();
//      try {
//			Query query = new Query();
//			query.addCriteria(where("createTime").is(endDateStr));
//			queryResults = mongoTemplate.find(query, scpool.class);
//      } catch (Exception e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//	  return queryResults;
//	}
//
//	@Override
//	public stkdj findSTKDJByCodeAndDate(String code, String date) throws Exception {
//		stkdj stkdj = null;
//        try {
//        	Query query = new Query();
//
//        	query.addCriteria(where("stockcode").is(code));
//        	query.addCriteria(where("datep").is(date));
//        	query.with(new Sort(new Order(Direction.DESC,"datep")));
//
//			List<stkdj> queryResults = mongoTemplate.find(query, stkdj.class);
//			if(queryResults!=null && queryResults.size()>0){
//				for (stkdj result : queryResults) {
//					stkdj = result;
//					break;
//				}
//			}else{
//				return null;
//			}
//		 } catch (Exception e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//		return stkdj;
//	}
//
//
//	@Override
//	public List<stkdj> findSTKDJByCodeAndBeforeDate(String code, String date) throws Exception {
//		List<stkdj> queryResults = null;
//        try {
//        	Query query = new Query();
//
//        	query.addCriteria(where("stockcode").is(code));
//        	query.addCriteria(where("datep").lte(date));
//        	query.with(new Sort(new Order(Direction.DESC,"datep")));
//
//			queryResults = mongoTemplate.find(query, stkdj.class);
//
//		 } catch (Exception e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//		return queryResults;
//	}
//

}
