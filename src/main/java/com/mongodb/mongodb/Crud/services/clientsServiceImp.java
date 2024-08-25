package com.mongodb.mongodb.Crud.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.Crud.entities.Clients;
import com.mongodb.mongodb.Crud.repositories.clientsRepository;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;

@Service
public class clientsServiceImp implements clientsService {

    @Autowired
    private clientsRepository repository;

    @Override
    public Clients addClients(Clients clients) {


        if(!repository.existsByName(clients.getName())){
            return  repository.save(clients);
        }else{
            throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }


    }

    @Override
    public void deleteClients(ObjectId id) {
        Optional<Clients> LFClients  = repository.findById(id);
        if(LFClients.isPresent()){
            Clients ClientFound = LFClients.get();
            repository.delete(ClientFound);
        } 
       
        
    }

    @Override
    public Clients updateClients(ObjectId id, Clients clients) {

        Optional<Clients> LFClient = repository.findById(id);
        if(LFClient.isPresent()){
            return repository.save(clients);
        }

        throw new notFoundException("Client wasnt found");
       
    }

    @Override
    public Clients getClient(ObjectId id) {
        Optional<Clients> LFClient = repository.findById(id);

        return LFClient.get();
    }

    @Override
    public List<Clients> getAllClients() {
    
        return repository.findAll();
    }

}
