package com.mongodb.mongodb.nombre.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;
import com.mongodb.mongodb.nombre.entities.RoomType;
import com.mongodb.mongodb.nombre.repositories.roomtypeRepository;
import com.mongodb.mongodb.utils.operations;

@Service
public class roomtypeServiceImp implements roomtypeService {

    @Autowired
    private roomtypeRepository repository;

    @Override
    public RoomType addRoomType(RoomType roomType) {
        long id = operations.autoIncrement(repository.findAll());
        RoomType roomtypeToSave =  new RoomType(id, roomType.getName(),roomType.getNumberOfBeds(),roomType.getArea());
        if(!repository.existsByName(roomType.getName())){
            return  repository.save(roomtypeToSave);
        }else{
            throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }
    }

    @Override
    public void deleteRoomtypes(Long id) {
        Optional<RoomType> LFRoomtypes  = repository.findById(id);
        if(LFRoomtypes.isPresent()){
            RoomType roomFound = LFRoomtypes.get();
            repository.delete(roomFound);
        } else{
            throw new notFoundException(AppConstants.DOESNT_EXIST);
        }

    }

    @Override
    public RoomType updateRoomtypes(Long id, RoomType roomType)  {
        Optional<RoomType> LFRoomtypes = repository.findById(id);
        if(LFRoomtypes.isPresent()){
            return repository.save(roomType);
        }
         throw new notFoundException(AppConstants.DOESNT_EXIST);
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
