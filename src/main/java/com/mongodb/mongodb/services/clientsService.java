package com.mongodb.mongodb.services;

import java.util.List;
import java.util.Optional;

import com.mongodb.mongodb.entities.Clients;

public interface clientsService {

    Clients addClients(Clients clients);
    void deleteClients(Long id);
    Optional<Clients> updateClients(Long id, Clients clients);
    Clients getClient(Long id);
    List<Clients> getAllClients();
}
