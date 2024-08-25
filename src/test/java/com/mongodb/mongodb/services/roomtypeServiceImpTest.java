package com.mongodb.mongodb.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongodb.mongodb.Crud.entities.RoomType;
import com.mongodb.mongodb.Crud.repositories.roomtypeRepository;
import com.mongodb.mongodb.Crud.services.roomtypeServiceImp;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;

@ExtendWith(MockitoExtension.class)
public class roomtypeServiceImpTest {

   @Mock
    private roomtypeRepository repository;

    @InjectMocks
    private roomtypeServiceImp service;

    public  RoomType ROOMTYPE_TEST = new RoomType(new ObjectId("123456abcdef123456abcdef"), "TYPE1", "SOMETHING","SOMETHINGELSE");
    public  RoomType ROOMTYPE_TEST_UPDATE = new RoomType(new ObjectId("123456abcdef123456abcdef"), "TYPE2","SOMETHING SOMETHING", "SOMETHINGELSE");
    

    @Test
    void testAddRoomType() {
        when(repository.existsByName(ROOMTYPE_TEST.getName())).thenReturn(false);
        when(repository.save(any(RoomType.class))).thenReturn(ROOMTYPE_TEST);

        RoomType actual = service.addRoomType(ROOMTYPE_TEST); 

        assertAll(
            // () -> assertNotNull(ROOMTYPE_TEST),
            () -> assertEquals(ROOMTYPE_TEST, actual)
            
            );
            
            verify(repository).save(ROOMTYPE_TEST);
    } 

 
@Test
void testAddRoomtypeError() {
    when(repository.existsByName(ROOMTYPE_TEST.getName())).thenReturn(true);
    Exception exception  = assertThrows(alreadyExistException.class,
    () -> service.addRoomType(ROOMTYPE_TEST));
    String error = exception.getMessage();
    
    assertAll(
        () -> assertNotNull(ROOMTYPE_TEST),
        () -> assertEquals(error, "OBJECT ALREADY EXISTS")
        
        );

        verify(repository).existsByName(ROOMTYPE_TEST.getName());
    } 
    
    
    @Test
    void testDeleteRoomtype() {
        
    when(repository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(ROOMTYPE_TEST));
    doNothing().when(repository).delete(any(RoomType.class));
    
    service.deleteRoomtypes(new ObjectId("123456abcdef123456abcdef"));
    
    verify(repository).findById(new ObjectId("123456abcdef123456abcdef"));
    verify(repository).delete(ROOMTYPE_TEST);
    
}

@Test
void testGetAllRoomtypes() {
    when(repository.findAll()).thenReturn(List.of(ROOMTYPE_TEST, ROOMTYPE_TEST_UPDATE));
    
    List<RoomType> actual = service.getAllRoomtypes();
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(List.of(ROOMTYPE_TEST,ROOMTYPE_TEST_UPDATE), actual)
        );
        
        verify(repository).findAll();
        
    }
    
    @Test
    void testGetRoomtypes() {
        
    when(repository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(ROOMTYPE_TEST));
    
    RoomType actual = service.getRoomtype(new ObjectId("123456abcdef123456abcdef"));
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(ROOMTYPE_TEST, actual)
        );
        
        verify(repository).findById(new ObjectId("123456abcdef123456abcdef"));
        
        
    }
    
    @Test
    void testUpdateRoomtype() {
        
    when(repository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(ROOMTYPE_TEST));
    when(repository.save(any(RoomType.class))).thenReturn(ROOMTYPE_TEST_UPDATE);
    
    RoomType actual = service.updateRoomtypes(new ObjectId("123456abcdef123456abcdef"), ROOMTYPE_TEST_UPDATE);
    
    Exception exception = assertThrows(notFoundException.class,
    () -> service.updateRoomtypes(new ObjectId("123456abcdef123456abcde2"), ROOMTYPE_TEST_UPDATE));

    String error = exception.getMessage();
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(ROOMTYPE_TEST_UPDATE, actual),
        () -> assertEquals(error, "COULDN'T FIND THE OBJECT") 
        
        );
        
        verify(repository).findById(new ObjectId("123456abcdef123456abcdef"));
        verify(repository).save(ROOMTYPE_TEST_UPDATE);
        
    }
}
