package com.mongodb.mongodb.Crud.entities;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Rooms")
public class Rooms  {

    @Id
    private ObjectId id;
    private RoomType type;
    private String beds;
    private String price;
    //falta fecha

 

    

}
