package com.mongodb.mongodb.nombre.entities;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RoomTypes")
public class RoomType extends idDocument {

    private String name;
    private String numberOfBeds;
    private String area;
    
    @Override
    public Long getId() {
        return super.getId();
    }
    @Override
    public void setId(Long id) {
        super.setId(id);
    }
    public RoomType(Long id,String name, String numberOfBeds, String area) {
        this.id = id;
        this.name = name;
        this.numberOfBeds = numberOfBeds;
        this.area = area;
    }

    

}
