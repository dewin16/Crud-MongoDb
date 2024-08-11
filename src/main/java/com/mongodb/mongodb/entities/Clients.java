package com.mongodb.mongodb.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "clients")
public class Clients {

    @Id
    private Long id;
    private String name;
    private String phoneNumber;
    private String mail;

}
