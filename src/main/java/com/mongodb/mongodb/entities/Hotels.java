package com.mongodb.mongodb.entities;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.mongodb.models.Adresses;
import com.mongodb.mongodb.models.Reviews;

import lombok.Data;

@Data
@Document(collection = "hotels")
public class Hotels {

    @Id
    private Long id;
    private String name;
    private List<Adresses> adress;
    private List<RoomType> roomtypes;
    private List<Clients> clients;
    private List<Reviews> reviews;
    private Map<String, ?> randomFields;


}
