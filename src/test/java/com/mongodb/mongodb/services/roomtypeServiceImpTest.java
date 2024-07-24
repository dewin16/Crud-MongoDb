package com.mongodb.mongodb.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.mongodb.mongodb.nombre.entities.RoomType;
import com.mongodb.mongodb.nombre.repositories.roomtypeRepository;
import com.mongodb.mongodb.nombre.services.roomtypeServiceImp;

@ExtendWith(MockitoExtension.class)
public class roomtypeServiceImpTest {

   @Mock
    private roomtypeRepository repository;

    @InjectMocks
    private roomtypeServiceImp service;

    @Test
    void testAddRoomType() {
        when(repository.existsByName(AppConstants.ROOMTYPE_TEST.getName())).thenReturn(false);
        when(repository.save(any(RoomType.class))).thenReturn(AppConstants.ROOMTYPE_TEST);

        RoomType actual = service.addRoomType(AppConstants.ROOMTYPE_TEST); 

        assertAll(
            () -> assertNotNull(AppConstants.ROOMTYPE_TEST),
            () -> assertEquals(AppConstants.ROOMTYPE_TEST, actual)
            
            );
            
            verify(repository).save(AppConstants.ROOMTYPE_TEST);
    } 

 
@Test
void testAddRoomtypeError() {
    when(repository.existsByName(AppConstants.ROOMTYPE_TEST.getName())).thenReturn(true);
    Exception exception  = assertThrows(alreadyExistException.class,
    () -> service.addRoomType(AppConstants.ROOMTYPE_TEST));
    String error = exception.getMessage();
    
    assertAll(
        () -> assertNotNull(AppConstants.ROOMTYPE_TEST),
        () -> assertEquals(error, "OBJECT ALREADY EXISTS")
        
        );

        verify(repository).existsByName(AppConstants.ROOMTYPE_TEST.getName());
    } 
    
    
    @Test
    void testDeleteRoomtype() {
        
    when(repository.findById(anyLong())).thenReturn(Optional.of(AppConstants.ROOMTYPE_TEST));
    doNothing().when(repository).delete(any(RoomType.class));
    
    service.deleteRoomtypes(anyLong());
    
    verify(repository).findById(anyLong());
    verify(repository).delete(AppConstants.ROOMTYPE_TEST);
    
}

@Test
void testGetAllRoomtypes() {
    when(repository.findAll()).thenReturn(List.of(AppConstants.ROOMTYPE_TEST, AppConstants.ROOMTYPE_TEST_UPDATE));
    
    List<RoomType> actual = service.getAllRoomtypes();
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(List.of(AppConstants.ROOMTYPE_TEST,AppConstants.ROOMTYPE_TEST_UPDATE), actual)
        );
        
        verify(repository).findAll();
        
    }
    
    @Test
    void testGetRoomtypes() {
        
    when(repository.findById(anyLong())).thenReturn(Optional.of(AppConstants.ROOMTYPE_TEST));
    
    RoomType actual = service.getRoomtype(anyLong());
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(AppConstants.ROOMTYPE_TEST, actual)
        );
        
        verify(repository).findById(anyLong());
        
        
    }
    
    @Test
    void testUpdateRoomtype() {
        
    when(repository.findById(1L)).thenReturn(Optional.of(AppConstants.ROOMTYPE_TEST));
    when(repository.save(any(RoomType.class))).thenReturn(AppConstants.ROOMTYPE_TEST_UPDATE);
    
    RoomType actual = service.updateRoomtypes(1L, AppConstants.ROOMTYPE_TEST_UPDATE);
    
    Exception exception = assertThrows(notFoundException.class,
    () -> service.updateRoomtypes(15L, AppConstants.ROOMTYPE_TEST_UPDATE));

    String error = exception.getMessage();
    
    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(AppConstants.ROOMTYPE_TEST_UPDATE, actual),
        () -> assertEquals(error, "COULDN'T FIND THE OBJECT")  //
        
        );
        
        verify(repository).findById(1L);
        verify(repository).save(AppConstants.ROOMTYPE_TEST_UPDATE);
        
    }
}
