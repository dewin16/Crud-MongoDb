package com.mongodb.mongodb.nombre.controller;

import java.util.List;

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

import com.mongodb.mongodb.nombre.entities.Rooms;
import com.mongodb.mongodb.nombre.services.roomsServiceImp;

@RestController
@RequestMapping("/rooms")
public class roomsController {
  @Autowired
    private roomsServiceImp service;


    @GetMapping("/findAll")
    private List<Rooms> findAll(){

        return service.getAllRooms();

    }

    @GetMapping("/find/{id}")
    private Rooms findById(@PathVariable Long id){

        return service.getRoom(id);

    }

    @PostMapping("/add")
    private Rooms add(@RequestBody Rooms room){

        return service.addRooms(room);

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){

        try{
            service.deleteRooms(id);
            return ResponseEntity.ok().body("The restaurant was deleted");
        }catch(Exception e){
         return ResponseEntity.badRequest().body("Couldn't find the restaurant");   
        }
    
    }

    @PutMapping("update/{id}")
    
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Rooms room) {

        try{
            service.updateRooms(id, room);
            return ResponseEntity.ok().body("Updated");

        }catch(Exception e){
            return ResponseEntity.badRequest().body("Couldn't find the restaurant");
        }

    }
}
