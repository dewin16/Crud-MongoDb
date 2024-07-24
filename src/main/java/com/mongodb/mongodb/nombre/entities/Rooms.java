package com.mongodb.mongodb.nombre.entities;


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
public class Rooms extends idDocument {

   
    private RoomType type;
    private String beds;
    private String price;
    //falta fecha

    @Override
    public Long getId() {
        return super.getId();
    }
    @Override
    public void setId(Long id) {
        super.setId(id);
    }
    public Rooms(Long id,RoomType type, String beds, String price) {
        this.id = id;
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    

}
