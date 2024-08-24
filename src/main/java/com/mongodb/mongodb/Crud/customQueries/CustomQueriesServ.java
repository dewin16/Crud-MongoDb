package com.mongodb.mongodb.nombre.customQueries;

import java.util.List;

import com.mongodb.mongodb.nombre.entities.Hotels;

public interface CustomQueriesServ {

    List<Hotels> updateReviews(Long hotelId, Long reviewId, String newReview);
}
