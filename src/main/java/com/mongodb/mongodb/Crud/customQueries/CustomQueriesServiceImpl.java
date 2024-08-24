package com.mongodb.mongodb.nombre.customQueries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.nombre.entities.Hotels;

@Service
public class CustomQueriesServiceImpl implements CustomQueriesServ {
   
    @Autowired
    private  MongoTemplate mongoTemplate;

    
    @Override
    public List<Hotels> updateReviews(Long hotelId, Long reviewId, String newReview) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(hotelId));
        query.addCriteria(Criteria.where("reviews.id").is(reviewId));
        Update update = new Update();
        update.set("reviews.$.text", newReview);
        mongoTemplate.updateFirst(query,update, Hotels.class);
        return mongoTemplate.find(query, Hotels.class);
        
    }
    


  

}
