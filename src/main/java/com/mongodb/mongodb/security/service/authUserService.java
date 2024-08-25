package com.mongodb.mongodb.security.service;



import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.security.dto.createDto;
import com.mongodb.mongodb.security.dto.jwtDto;
import com.mongodb.mongodb.security.dto.loginDto;
import com.mongodb.mongodb.security.entities.authUser;
import com.mongodb.mongodb.security.enums.rolesEnum;
import com.mongodb.mongodb.security.jwt.jwtService;
import com.mongodb.mongodb.security.repository.authUserRepository;

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

    public authUser create(createDto dto){
        if(repository.existsByUsername(dto.getUsername())){
            throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }else{
           
                return repository.save(mapFromDto(dto));
            }
    }

    public jwtDto login(loginDto logindto){
        
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
        (logindto.getUsername(), logindto.getPassword()));
        
        UserDetails user = repository.findByUsernameOrEmail(logindto.getUsername(), logindto.getUsername()).get();
        String token = provider.getToken(user);

        return new jwtDto(token);

    }

    private authUser mapFromDto(createDto dto){


        
        List<rolesEnum> roles = 
        dto.getRoles().stream().map(rol -> rolesEnum.valueOf(rol)).collect(Collectors.toList());

        authUser authUser = new authUser(new ObjectId(),dto.getUsername(),
         dto.getEmail(), 
        passwordEncoder.encode(dto.getPassword()), roles
        );
        
        return authUser;

          
        }
}
