package com.mongodb.mongodb.nombre.services;

import java.util.List;

import com.mongodb.mongodb.nombre.entities.Clients;

public interface clientsService {

    Clients addClients(Clients clients);
    void deleteClients(Long id);
    Clients updateClients(Long id, Clients clients);
    Clients getClient(Long id);
    List<Clients> getAllClients();
}
