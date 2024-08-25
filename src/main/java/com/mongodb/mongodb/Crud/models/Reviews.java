package com.mongodb.mongodb.Crud.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reviews {

    @Id
    private ObjectId id;
    private String clientName;
    private int score;
    private String text;

}
