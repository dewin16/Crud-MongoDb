package com.mongodb.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.entities.Clients;
import com.mongodb.mongodb.repositories.clientsRepository;

@Service
public class clientsServiceImp implements clientsService {

    @Autowired
    private clientsRepository repository;

    @Override
    public Clients addClients(Clients clients) {
        return  repository.save(clients);

    }

    @Override
    public void deleteClients(Long id) {
        Optional<Clients> LFClients  = repository.findById(id);
        if(LFClients.isPresent()){
            Clients ClientFound = LFClients.get();
            repository.delete(ClientFound);
        } 
       
        
    }

    @Override
    public Optional<Clients> updateClients(Long id, Clients clients) {

        Optional<Clients> LFClient = repository.findById(id);
        if(LFClient.isPresent()){
            return Optional.of(repository.save(clients));
        }

        return LFClient;
       
    }

    @Override
    public Clients getClient(Long id) {
        Optional<Clients> LFClient = repository.findById(id);

        return LFClient.get();
    }

    @Override
    public List<Clients> getAllClients() {
    
        return repository.findAll();
    }

}
