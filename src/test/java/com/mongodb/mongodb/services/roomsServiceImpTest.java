package com.mongodb.mongodb.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;
import com.mongodb.mongodb.nombre.entities.Rooms;
import com.mongodb.mongodb.nombre.repositories.roomsRepository;
import com.mongodb.mongodb.nombre.services.roomsServiceImp;

@ExtendWith(MockitoExtension.class)
public class roomsServiceImpTest {

    @Mock
    private roomsRepository repository;

    @InjectMocks
    private roomsServiceImp service;

    @Test
    void testAddRooms() {
        when(repository.existsByName(AppConstants.ROOM_TEST.getType().getName())).thenReturn(false);
        when(repository.save(any(Rooms.class))).thenReturn(AppConstants.ROOM_TEST);

        Rooms actualRoom = service.addRooms(AppConstants.ROOM_TEST); 

        assertAll(
            () -> assertNotNull(AppConstants.ROOM_TEST),
            () -> assertEquals(AppConstants.ROOM_TEST, actualRoom)
            
            );
            
            verify(repository).save(AppConstants.ROOM_TEST);
    } 

 
@Test
void testAddHotelsError() {
    when(repository.existsByName(AppConstants.ROOM_TEST.getType().getName())).thenReturn(true);
    Exception exception  = assertThrows(alreadyExistException.class,
    () -> service.addRooms(AppConstants.ROOM_TEST));
    String error = exception.getMessage();
    
    assertAll(
        () -> assertNotNull(AppConstants.ROOM_TEST),
        () -> assertEquals(error, "OBJECT ALREADY EXISTS")
        
        );

        verify(repository).existsByName(AppConstants.ROOM_TEST.getType().getName());
    } 
    
    
    @Test
    void testDeleteHotels() {
        
    when(repository.findById(anyLong())).thenReturn(Optional.of(AppConstants.ROOM_TEST));
    doNothing().when(repository).delete(any(Rooms.class));
    
    service.deleteRooms(anyLong());
    
    verify(repository).findById(anyLong());
    verify(repository).delete(AppConstants.ROOM_TEST);
    
}

@Test
void testGetAllHotels() {
    when(repository.findAll()).thenReturn(List.of(AppConstants.ROOM_TEST, AppConstants.ROOM_TEST_UPDATE));
    
    List<Rooms> actualRoom = service.getAllRooms();
    
    assertAll(
        () -> assertNotNull(actualRoom),
        () -> assertEquals(List.of(AppConstants.ROOM_TEST,AppConstants.ROOM_TEST_UPDATE), actualRoom)
        );
        
        verify(repository).findAll();
        
    }
    
    @Test
    void testGetHotels() {
        
    when(repository.findById(anyLong())).thenReturn(Optional.of(AppConstants.ROOM_TEST));
    
    Rooms actual = service.getRoom(anyLong());
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(AppConstants.ROOM_TEST, actual)
        );
        
        verify(repository).findById(anyLong());
        
        
    }
    
    @Test
    void testUpdateHotels() {
        
    when(repository.findById(1L)).thenReturn(Optional.of(AppConstants.ROOM_TEST));
    when(repository.save(any(Rooms.class))).thenReturn(AppConstants.ROOM_TEST_UPDATE);
    
    Rooms actual = service.updateRooms(1L, AppConstants.ROOM_TEST_UPDATE);
    
    Exception exception = assertThrows(notFoundException.class,
    () -> service.updateRooms(15L, AppConstants.ROOM_TEST_UPDATE));

    String error = exception.getMessage();
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(AppConstants.ROOM_TEST_UPDATE, actual),
        () -> assertEquals(error, "COULDN'T FIND THE OBJECT")  //
        
        );
        
        verify(repository).findById(1L);
        verify(repository).save(AppConstants.ROOM_TEST_UPDATE);
        
    }
   
}