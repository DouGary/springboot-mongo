package com.dc.dal;

import com.dc.model.LongTouIndexPro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LongTouIndexProRepository extends MongoRepository<LongTouIndexPro, String>, LongTouIndexProRepositoryDAL {

}
