package com.mongodb.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.entities.Rooms;
import com.mongodb.mongodb.repositories.roomsRepository;

@Service
public class roomsServiceImp implements roomsService {

    @Autowired
    private roomsRepository repository;

    @Override
    public Rooms addRooms(Rooms rooms) {
        return  repository.save(rooms);
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
    public Optional<Rooms> updateRooms(Long id, Rooms rooms) {
     
        Optional<Rooms> LFRooms = repository.findById(id);
        if(LFRooms.isPresent()){
            return Optional.of(repository.save(rooms));
        }

        return LFRooms;
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
