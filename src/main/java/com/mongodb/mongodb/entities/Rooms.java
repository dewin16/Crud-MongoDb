package com.mongodb.mongodb.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Rooms")
public class Rooms {

    @Id
    private Long id;
    private RoomType type;
    private String beds;
    private String price;
    //falta fecha


}
