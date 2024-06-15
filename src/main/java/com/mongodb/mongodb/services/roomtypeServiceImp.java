package com.mongodb.mongodb.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.entities.RoomType;
import com.mongodb.mongodb.repositories.roomtypeRepository;

@Service
public class roomtypeServiceImp implements roomtypeService {

    @Autowired
    private roomtypeRepository repository;

    @Override
    public RoomType addRoomType(RoomType roomType) {
        return  repository.save(roomType);
    }

    @Override
    public void deleteRoomtypes(Long id) {
        Optional<RoomType> LFRoomtypes  = repository.findById(id);
        if(LFRoomtypes.isPresent()){
            RoomType roomFound = LFRoomtypes.get();
            repository.delete(roomFound);
        } 
        
    }

    @Override
    public Optional<RoomType> updateRoomtypes(Long id, RoomType roomType)  {
        Optional<RoomType> LFRoomtypes = repository.findById(id);
        if(LFRoomtypes.isPresent()){
            return Optional.of(repository.save(roomType));
        }
        return LFRoomtypes;
        // throw new NameNotFoundException("Room doesn't exist");
    }

    @Override
    public RoomType getRoomtype(Long id) {
       Optional<RoomType> LFRoomtype = repository.findById(id);

        return LFRoomtype.get();
    }

    @Override
    public List<RoomType> getAllRoomtypes() {
       return repository.findAll();
    }

}
