package com.mongodb.mongodb.nombre.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;
import com.mongodb.mongodb.nombre.entities.Rooms;
import com.mongodb.mongodb.nombre.repositories.roomsRepository;
import com.mongodb.mongodb.utils.operations;

@Service
public class roomsServiceImp implements roomsService {

    @Autowired
    private roomsRepository repository;

    @Override
    public Rooms addRooms(Rooms rooms) {
        Long id = operations.autoIncrement(repository.findAll());
        Rooms roomToSave = new Rooms(id, rooms.getType(), rooms.getBeds(), rooms.getPrice());
        if(!repository.existsByName(rooms.getType().getName())){
            return repository.save(roomToSave);
        }else{
             throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }
    }

    @Override
    public void deleteRooms(Long id) {
       Optional<Rooms> LFRooms  = repository.findById(id);
        if(LFRooms.isPresent()){
            Rooms roomFound = LFRooms.get();
            repository.delete(roomFound);
        } 
    }

    @Override
    public Rooms updateRooms(Long id, Rooms rooms) {
     
        Optional<Rooms> LFRooms = repository.findById(id);
        if(LFRooms.isPresent()){
            return repository.save(rooms);
        }else{
            throw new notFoundException(AppConstants.DOESNT_EXIST);
        }

    }

    @Override
    public Rooms getRoom(Long id) {
        Optional<Rooms> LFRoom = repository.findById(id);

        return LFRoom.get();
    
    }

    @Override
    public List<Rooms> getAllRooms() {
        return repository.findAll();
    }

}
