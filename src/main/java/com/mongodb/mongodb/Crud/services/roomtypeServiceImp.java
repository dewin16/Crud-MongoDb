package com.mongodb.mongodb.Crud.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.Crud.entities.RoomType;
import com.mongodb.mongodb.Crud.repositories.roomtypeRepository;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;

@Service
public class roomtypeServiceImp implements roomtypeService {

    @Autowired
    private roomtypeRepository repository;

    @Override
    public RoomType addRoomType(RoomType roomType) {
        if(!repository.existsByName(roomType.getName())){
            return  repository.save(roomType);
        }else{
            throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }
    }

    @Override
    public void deleteRoomtypes(ObjectId id) {
        Optional<RoomType> LFRoomtypes  = repository.findById(id);
        if(LFRoomtypes.isPresent()){
            RoomType roomFound = LFRoomtypes.get();
            repository.delete(roomFound);
        } else{
            throw new notFoundException(AppConstants.DOESNT_EXIST);
        }

    }

    @Override
    public RoomType updateRoomtypes(ObjectId id, RoomType roomType)  {
        Optional<RoomType> LFRoomtypes = repository.findById(id);
        if(LFRoomtypes.isPresent()){
            return repository.save(roomType);
        }
         throw new notFoundException(AppConstants.DOESNT_EXIST);
    }

    @Override
    public RoomType getRoomtype(ObjectId id) {
       Optional<RoomType> LFRoomtype = repository.findById(id);

        return LFRoomtype.get();
    }

    @Override
    public List<RoomType> getAllRoomtypes() {
       return repository.findAll();
    }

}
