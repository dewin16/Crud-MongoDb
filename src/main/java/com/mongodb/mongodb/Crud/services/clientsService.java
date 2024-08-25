package com.mongodb.mongodb.Crud.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.mongodb.Crud.entities.Clients;

public interface clientsService {

    Clients addClients(Clients clients);
    void deleteClients(ObjectId id);
    Clients updateClients(ObjectId id, Clients clients);
    Clients getClient(ObjectId id);
    List<Clients> getAllClients();
}
