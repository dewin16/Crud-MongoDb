package com.mongodb.mongodb.services;

import static org.junit.jupiter.api.Assertions.*;
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

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.Crud.entities.RoomType;
import com.mongodb.mongodb.Crud.entities.Rooms;
import com.mongodb.mongodb.Crud.repositories.roomsRepository;
import com.mongodb.mongodb.Crud.services.roomsServiceImp;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;

@ExtendWith(MockitoExtension.class)
public class roomsServiceImpTest {

    @Mock
    private roomsRepository repository;

    @InjectMocks
    private roomsServiceImp service;


    public  RoomType ROOMTYPE_TEST = new RoomType(new ObjectId("123456abcdef123456abcdef"), "TYPE1", "SOMETHING","SOMETHINGELSE");
    public  RoomType ROOMTYPE_TEST_UPDATE = new RoomType(new ObjectId("123456abcdef123456abcdef"), "TYPE2","SOMETHING SOMETHING", "SOMETHINGELSE");
    
    public  Rooms ROOM_TEST = new Rooms(new ObjectId("123456abcdef123456abcdef"),ROOMTYPE_TEST, "a","b");
    public  Rooms ROOM_TEST_UPDATE = new Rooms(new ObjectId("123456abcdef123456abcdef"),ROOMTYPE_TEST,"updated","updated");
    

    @Test
    void testAddRooms() {
        when(repository.existsByName(ROOM_TEST.getType().getName())).thenReturn(false);
        when(repository.save(any(Rooms.class))).thenReturn(ROOM_TEST);

        Rooms actualRoom = service.addRooms(ROOM_TEST); 

        assertAll(
            () -> assertNotNull(ROOM_TEST),
            () -> assertEquals(ROOM_TEST, actualRoom)
            
            );
            
            verify(repository).save(ROOM_TEST);
    } 

 
@Test
void testAddHotelsError() {
    when(repository.existsByName(ROOM_TEST.getType().getName())).thenReturn(true);
    Exception exception  = assertThrows(alreadyExistException.class,
    () -> service.addRooms(ROOM_TEST));
    String error = exception.getMessage();
    
    assertAll(
        () -> assertNotNull(ROOM_TEST),
        () -> assertEquals(error, "OBJECT ALREADY EXISTS")
        
        );

        verify(repository).existsByName(ROOM_TEST.getType().getName());
    } 
    
    
    @Test
    void testDeleteHotels() {
        
    when(repository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(ROOM_TEST));
    doNothing().when(repository).delete(any(Rooms.class));
    
    service.deleteRooms(new ObjectId("123456abcdef123456abcdef"));
    
    verify(repository).findById(new ObjectId("123456abcdef123456abcdef"));
    verify(repository).delete(ROOM_TEST);
    
}

@Test
void testGetAllHotels() {
    when(repository.findAll()).thenReturn(List.of(ROOM_TEST, ROOM_TEST_UPDATE));
    
    List<Rooms> actualRoom = service.getAllRooms();
    
    assertAll(
        () -> assertNotNull(actualRoom),
        () -> assertEquals(List.of(ROOM_TEST,ROOM_TEST_UPDATE), actualRoom)
        );
        
        verify(repository).findAll();
        
    }
    
    @Test
    void testGetHotels() {
        
    when(repository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(ROOM_TEST));
    
    Rooms actual = service.getRoom(new ObjectId("123456abcdef123456abcdef"));
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(ROOM_TEST, actual)
        );
        
        verify(repository).findById(new ObjectId("123456abcdef123456abcdef"));
        
        
    }
    
    @Test
    void testUpdateHotels() {
        
    when(repository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(ROOM_TEST));
    when(repository.save(any(Rooms.class))).thenReturn(ROOM_TEST_UPDATE);
    
    Rooms actual = service.updateRooms(new ObjectId("123456abcdef123456abcdef"), ROOM_TEST_UPDATE);
    
    Exception exception = assertThrows(notFoundException.class,
    () -> service.updateRooms(new ObjectId("123456abcdef123456abcde2"), ROOM_TEST_UPDATE));

    String error = exception.getMessage();
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(ROOM_TEST_UPDATE, actual),
        () -> assertEquals(error, "COULDN'T FIND THE OBJECT")  //
        
        );
        
        verify(repository).findById(new ObjectId("123456abcdef123456abcdef"));
        verify(repository).save(ROOM_TEST_UPDATE);
        
    }
   
}