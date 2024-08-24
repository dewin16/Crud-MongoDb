package com.mongodb.mongodb.nombre.entities;

import java.util.List;
import java.util.Map;


import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.mongodb.nombre.models.Adresses;
import com.mongodb.mongodb.nombre.models.Reviews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "hotels")
public class Hotels extends idDocument {


    private String name;
    private List<Adresses> adress;
    private List<RoomType> roomtypes;
    private List<Clients> clients;
    private List<Reviews> reviews;
    private Map<String, ?> randomFields;

    
    
    @Override
    public Long getId() {
        return super.getId();
    }



    @Override
    public void setId(Long id) {
        super.setId(id);
    }



    public Hotels(Long id, String name, List<Adresses> adress, List<RoomType> roomtypes, List<Clients> clients,
            List<Reviews> reviews) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.roomtypes = roomtypes;
        this.clients = clients;
        this.reviews = reviews;
    }

    


}
