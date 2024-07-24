package com.mongodb.mongodb.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;


@Configuration
public class mongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
       return "API_Hoteles";
    }


}
