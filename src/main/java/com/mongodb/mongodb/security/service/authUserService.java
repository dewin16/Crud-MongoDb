package com.mongodb.mongodb.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.security.dto.createDto;
import com.mongodb.mongodb.security.dto.jwtDto;
import com.mongodb.mongodb.security.dto.loginDto;
import com.mongodb.mongodb.security.entities.authUser;
import com.mongodb.mongodb.security.enums.rolesEnum;
import com.mongodb.mongodb.security.jwt.jwtService;
import com.mongodb.mongodb.security.repository.authUserRepository;
import com.mongodb.mongodb.Constants.AppConstants.*;

@Service
public class authUserService {

    @Autowired
    private authUserRepository repository;

    @Autowired
    private jwtService provider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public authUser create(createDto dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        } else {

            return repository.save(mapFromDto(dto));
        }
    }

    public jwtDto login(loginDto logindto) {

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword()));

        UserDetails user = repository.findByUsernameOrEmail(logindto.getUsername(), logindto.getUsername()).get();
        String token = provider.getToken(user);

        return new jwtDto(token);

    }

    private authUser mapFromDto(createDto dto) {

        List<rolesEnum> roles = dto.getRoles().stream().map(rol -> rolesEnum.valueOf(rol)).collect(Collectors.toList());

        authUser authUser = new authUser(new ObjectId(), dto.getUsername(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()), roles);

        return authUser;

    }

    public GoogleIdToken.Payload verify(String idTokenString) throws Exception {
        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(AppConstants.CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new RuntimeException("ID token inválido");
        }
    }

    public jwtDto googleTokenToSecurityToken(GoogleIdToken.Payload payload) {

       Optional<authUser> user = repository.findByUsernameOrEmail((String) payload.get("name"), payload.getEmail());
       List<rolesEnum> roles =  List.of(rolesEnum.ROLE_ADMIN);
       if(user.isPresent()){
            String token = provider.getToken(user.get());
            return new jwtDto(token);
        }else{
            authUser userToSave = new authUser();
            userToSave.setUsername((String) payload.get("name"));
            userToSave.setEmail(payload.getEmail());
            userToSave.setPassword(UUID.randomUUID().toString());
            userToSave.setRoles(roles);
            
            repository.save(userToSave);
            String token = provider.getToken(userToSave);
            return new jwtDto(token);
        }


    }
}
