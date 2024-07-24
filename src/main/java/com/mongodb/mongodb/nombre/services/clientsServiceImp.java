package com.mongodb.mongodb.nombre.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;
import com.mongodb.mongodb.nombre.entities.Clients;
import com.mongodb.mongodb.nombre.repositories.clientsRepository;
import com.mongodb.mongodb.utils.operations;

@Service
public class clientsServiceImp implements clientsService {

    @Autowired
    private clientsRepository repository;

    @Override
    public Clients addClients(Clients clients) {

        Long id = operations.autoIncrement(repository.findAll());
        Clients client = new Clients(id,clients.getName(),clients.getPhoneNumber(), clients.getMail());

        if(!repository.existsByName(clients.getName())){
            return  repository.save(client);
        }else{
            throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }


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
    public Clients updateClients(Long id, Clients clients) {

        Optional<Clients> LFClient = repository.findById(id);
        if(LFClient.isPresent()){
            return repository.save(clients);
        }

        throw new notFoundException("Client wasnt found");
       
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
