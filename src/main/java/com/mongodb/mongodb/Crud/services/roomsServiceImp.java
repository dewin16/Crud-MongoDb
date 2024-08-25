package com.mongodb.mongodb.Crud.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.Crud.entities.Rooms;
import com.mongodb.mongodb.Crud.repositories.roomsRepository;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;

@Service
public class roomsServiceImp implements roomsService {

    @Autowired
    private roomsRepository repository;

    @Override
    public Rooms addRooms(Rooms rooms) {
        if(!repository.existsByName(rooms.getType().getName())){
            return repository.save(rooms);
        }else{
             throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }
    }

    @Override
    public void deleteRooms(ObjectId id) {
       Optional<Rooms> LFRooms  = repository.findById(id);
        if(LFRooms.isPresent()){
            Rooms roomFound = LFRooms.get();
            repository.delete(roomFound);
        } 
    }

    @Override
    public Rooms updateRooms(ObjectId id, Rooms rooms) {
     
        Optional<Rooms> LFRooms = repository.findById(id);
        if(LFRooms.isPresent()){
            return repository.save(rooms);
        }else{
            throw new notFoundException(AppConstants.DOESNT_EXIST);
        }

    }

    @Override
    public Rooms getRoom(ObjectId id) {
        Optional<Rooms> LFRoom = repository.findById(id);

        return LFRoom.get();
    
    }

    @Override
    public List<Rooms> getAllRooms() {
        return repository.findAll();
    }

}
