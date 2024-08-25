package com.mongodb.mongodb.Crud.entities;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.mongodb.Crud.models.Adresses;
import com.mongodb.mongodb.Crud.models.Reviews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "hotels")
public class Hotels  {

    @Id
    private ObjectId id;
    private String name;
    private List<Adresses> adress;
    private List<RoomType> roomtypes;
    private List<Clients> clients;
    private List<Reviews> reviews;
    private Map<String, ?> randomFields;


    

}
