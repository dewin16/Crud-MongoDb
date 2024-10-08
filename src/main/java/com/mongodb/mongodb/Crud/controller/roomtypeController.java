package com.mongodb.mongodb.Crud.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.mongodb.Crud.entities.RoomType;
import com.mongodb.mongodb.Crud.services.roomtypeServiceImp;
import com.mongodb.mongodb.exceptions.notFoundException;

@RestController
@RequestMapping("/roomtype")
public class roomtypeController {
 @Autowired
    private roomtypeServiceImp service;


    @GetMapping("/findAll")
    private List<RoomType> findAll(){

        return service.getAllRoomtypes();

    }

    @GetMapping("/find/{id}")
    private RoomType findById(@PathVariable ObjectId id){

        return service.getRoomtype(id);

    }

    @PostMapping("/add")
    private RoomType add(@RequestBody RoomType roomtype){

        return service.addRoomType(roomtype);

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable ObjectId id){

        try{
            service.deleteRoomtypes(id);
            return ResponseEntity.ok().body("The restaurant was deleted");
        }catch(Exception e){
         return ResponseEntity.badRequest().body("Couldn't find the restaurant");   
        }
    
    }

    @PutMapping("update/{id}")
    
    public ResponseEntity<?> update(@PathVariable ObjectId id, @RequestBody RoomType roomtype) {

        try{
            service.updateRoomtypes(id, roomtype);
            return ResponseEntity.ok().body("Updated");

        }catch(notFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        
    }
}
