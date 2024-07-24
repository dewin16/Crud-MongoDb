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
@Document(collection = "clients")
public class Clients extends idDocument {
    
    private String name;
    private String phoneNumber;
    private String mail;


    
    @Override
    public Long getId() {
        return super.getId();
    }



    @Override
    public void setId(Long id) {
        super.setId(id);
    }



    public Clients(Long id, String name, String phoneNumber, String mail) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }
    
    

  

    

}
