package com.mongodb.mongodb.Constants;

import java.util.Arrays;
import java.util.List;

import com.mongodb.mongodb.nombre.entities.Clients;
import com.mongodb.mongodb.nombre.entities.Hotels;
import com.mongodb.mongodb.nombre.entities.RoomType;
import com.mongodb.mongodb.nombre.entities.Rooms;
import com.mongodb.mongodb.nombre.models.Adresses;
import com.mongodb.mongodb.nombre.models.Reviews;

public  class AppConstants {

    public static String DOESNT_EXIST = "COULDN'T FIND THE OBJECT";
    public static String ALREADY_EXIST = "OBJECT ALREADY EXISTS";

    public static final Clients CLIENT_TEST = new Clients(1L, "Juan","1111","juanpalomo@hotmail.com");
    public static final Clients CLIENT_TEST_UPDATE = new Clients(1L, "Pedro","1111","juanpalomo@hotmail.com");
   
    public static final List<Adresses> ADRESSES = Arrays.asList(new Adresses("a","b","c"), new Adresses("d","e","f"));
    public static final List<RoomType> ROOM_TYPES = Arrays.asList(new RoomType(1L,"a","b","c"), new RoomType(2L,"d","e","f"));
    public static final List<Clients> CLIENTS_LIST = Arrays.asList(CLIENT_TEST,CLIENT_TEST_UPDATE);
    public static final List<Reviews> REVIEWS = Arrays.asList(new Reviews(1L,"Pedro", 3, "TEXT"), new Reviews(2L,"Daniel",5,"OTHER TEXT"));
    public static final Hotels HOTEL_TEST = new Hotels(1L, "Hotel Las Vegas", ADRESSES, ROOM_TYPES, CLIENTS_LIST, REVIEWS);
    public static final Hotels HOTEL_TEST_UPDATE = new Hotels(1L, "patata", ADRESSES, ROOM_TYPES, CLIENTS_LIST, REVIEWS);

    public static final RoomType ROOMTYPE_TEST = new RoomType(1L, "TYPE1", "SOMETHING","SOMETHINGELSE");
    public static final RoomType ROOMTYPE_TEST_UPDATE = new RoomType(1l, "TYPE2","SOMETHING SOMETHING", "SOMETHINGELSE");
    
    public static final Rooms ROOM_TEST = new Rooms(1L,ROOMTYPE_TEST, "a","b");
    public static final Rooms ROOM_TEST_UPDATE = new Rooms(1L,ROOMTYPE_TEST,"updated","updated");
    
}