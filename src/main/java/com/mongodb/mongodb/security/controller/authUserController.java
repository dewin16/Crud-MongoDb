package com.mongodb.mongodb.security.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.mongodb.mongodb.security.dto.createDto;
import com.mongodb.mongodb.security.dto.jwtDto;
import com.mongodb.mongodb.security.dto.loginDto;
import com.mongodb.mongodb.security.entities.authUser;
import com.mongodb.mongodb.security.service.authUserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/auth")
@RestController
public class authUserController {

    @Autowired
    private authUserService service;
    
    @PostMapping(value = "/create",  produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> create(@RequestBody createDto dto){
        authUser authUser =  service.create(dto);
        return ResponseEntity.ok("WELCOME! " + authUser.getUsername());
    }

    @PostMapping(value = "/login",  produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> login(@RequestBody loginDto dto){

        jwtDto jwtdto = service.login(dto);
        return ResponseEntity.ok(jwtdto);
    }
    

       @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
        public ResponseEntity<String> authenticateWithGoogle(@RequestParam("credential") String idToken, HttpServletResponse response) {
        try {

            GoogleIdToken.Payload googleToken = service.verify(idToken);

            if(!service.verify(idToken).isEmpty()){
                jwtDto securityToken = service.googleTokenToSecurityToken(googleToken);
                Cookie tokenCookie = new Cookie("miSuperCookie", securityToken.toString());
                tokenCookie.setHttpOnly(true); 
                tokenCookie.setSecure(true);  
                tokenCookie.setPath("/");     
                tokenCookie.setMaxAge(3600); 
                
                response.addCookie(tokenCookie);

                response.sendRedirect( "http://localhost:4200/home");
                return ResponseEntity.ok().body("Se ha validado y enviado el token");
            }else{
                return ResponseEntity.status(401).body("Token invalido");
            }
        
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token inválido: " + e.getMessage());
        }
    }

}
