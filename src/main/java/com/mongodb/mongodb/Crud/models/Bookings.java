package com.mongodb.mongodb.Crud.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Bookings {

    private String client_id;
    private LocalDate checkin;
    private LocalDate checkout;

}
