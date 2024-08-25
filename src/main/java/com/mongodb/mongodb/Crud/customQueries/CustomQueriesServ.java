package com.mongodb.mongodb.Crud.customQueries;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.mongodb.Crud.entities.Hotels;

public interface CustomQueriesServ {

    List<Hotels> updateReviews(ObjectId hotelId, ObjectId reviewId, String newReview);
}
