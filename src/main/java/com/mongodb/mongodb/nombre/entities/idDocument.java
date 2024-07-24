package com.mongodb.mongodb.nombre.entities;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public abstract class idDocument {

    @Id
    protected Long id;

    



}
