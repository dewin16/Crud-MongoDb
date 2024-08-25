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

import com.mongodb.mongodb.Crud.entities.Clients;
import com.mongodb.mongodb.Crud.services.clientsServiceImp;

@RestController
@RequestMapping("/client")
public class clientController {

     @Autowired
    private clientsServiceImp service;


    @GetMapping("/findAll")
    private List<Clients> findAll(){

        return service.getAllClients();

    }

    @GetMapping("/find/{id}")
    private Clients findById(@PathVariable ObjectId id){

        return service.getClient(id);

    }

    @PostMapping("/add")
    private Clients add(@RequestBody Clients client){

        return service.addClients(client);

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable ObjectId id){

        try{
            service.deleteClients(id);
            return ResponseEntity.ok().body("The restaurant was deleted");
        }catch(Exception e){
         return ResponseEntity.badRequest().body("Couldn't find the restaurant");   
        }
    
    }

    @PutMapping("update/{id}")
    
    public ResponseEntity<?> update(@PathVariable ObjectId id, @RequestBody Clients restaurant) {

        try{
            service.updateClients(id, restaurant);
            return ResponseEntity.ok().body("Updated");

        }catch(Exception e){
            return ResponseEntity.badRequest().body("Couldn't find the restaurant");
        }

    }
}
