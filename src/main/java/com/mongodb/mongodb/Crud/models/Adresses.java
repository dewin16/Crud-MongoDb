package com.mongodb.mongodb.nombre.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresses {

    private String street;
    private String city;
    //pc =  postalcode
    private String PC;

}
