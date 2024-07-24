package com.mongodb.mongodb.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.mongodb.security.dto.createDto;
import com.mongodb.mongodb.security.dto.jwtDto;
import com.mongodb.mongodb.security.dto.loginDto;
import com.mongodb.mongodb.security.entities.authUser;
import com.mongodb.mongodb.security.service.authUserService;

@RequestMapping("/auth")
@RestController
public class authUserController {

    @Autowired
    private authUserService service;
    
    @PostMapping("/create")
    private ResponseEntity<?> create(@RequestBody createDto dto){
        authUser authUser =  service.create(dto);
        return ResponseEntity.ok(authUser.getUsername());
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody loginDto dto){

        jwtDto jwtdto = service.login(dto);
        return ResponseEntity.ok(jwtdto);
    }
    

}
