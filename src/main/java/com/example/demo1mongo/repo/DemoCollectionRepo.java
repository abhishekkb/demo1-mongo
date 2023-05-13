package com.example.demo1mongo.repo;

import com.example.demo1mongo.entity.SomeCollection;
import com.example.demo1mongo.model.DemoReq;
import com.example.demo1mongo.model.UpdateReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoCollectionRepo {
    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;

    public SomeCollection create(DemoReq dr){
        return mongoTemplate.save(SomeCollection.builder()
                .attr1(dr.getAttr1())
                .attr2(dr.getAttr2())
                .attr3(dr.getAttr3())
                .attr4(dr.getAttr4())
                .attr5(dr.getAttr5())
                .attr6(dr.getAttr6())
                .build());
    }

    public SomeCollection update(String id, DemoReq demoReq) {
        log.info("update by id");
        Criteria c = Criteria.where("_id").is(id);
        Query query = new Query().addCriteria(c);
        UpdateDefinition update = Update.update("attr7", demoReq.getAttr1()); //.set("attr7", demoReq.getAttr1());
        return mongoTemplate.findAndModify(query, update, SomeCollection.class);
    }

    public void update(UpdateReq updateReq, Map<String, String> allParams) {
        if(ObjectUtils.isEmpty(allParams)){
            throw new RuntimeException("Invalid request, empty query params");
        }
        log.info("update by query {}", allParams);
//        Criteria c = new Criteria();
//        for (var entry : allParams.entrySet()) {
//            c = c.and(entry.getKey()).is(entry.getValue());
//        }
        var c = Criteria.where("attr1").is(allParams.get("attr1"));
        Query q = new Query().addCriteria(c);
        UpdateDefinition ud = Update.update("attr7", updateReq.getAttr7());
        var result = mongoTemplate.updateMulti(q, ud, SomeCollection.class);
        log.info("updated records - {}", result.getModifiedCount());
    }
}
