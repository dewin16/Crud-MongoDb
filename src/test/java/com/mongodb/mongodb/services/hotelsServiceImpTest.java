package com.mongodb.mongodb.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongodb.mongodb.Crud.entities.Clients;
import com.mongodb.mongodb.Crud.entities.Hotels;
import com.mongodb.mongodb.Crud.entities.RoomType;
import com.mongodb.mongodb.Crud.models.Adresses;
import com.mongodb.mongodb.Crud.models.Reviews;
import com.mongodb.mongodb.Crud.repositories.hotelsRepository;
import com.mongodb.mongodb.Crud.services.hotelsServiceImp;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;

@ExtendWith(MockitoExtension.class)
public class hotelsServiceImpTest {

    @Mock
    private hotelsRepository hotelsRepository;

    @InjectMocks
    private hotelsServiceImp hotelsServiceImp;

    public  Clients CLIENT_TEST= new Clients(new ObjectId("123456abcdef123456abcdef"), "Juan","1111","juanpalomo@hotmail.com");
    public  Clients CLIENT_TEST_UPDATE = new Clients(new ObjectId("123456abcdef123456abcdef"), "Pedro","1111","juanpalomo@hotmail.com"); 
    public  List<Adresses> ADRESSES = Arrays.asList(new Adresses("a","b","c"), new Adresses("d","e","f"));
    public  List<RoomType> ROOM_TYPES = Arrays.asList(new RoomType(new ObjectId("123456abcdef123456abcdef"),"a","b","c"), new RoomType(new ObjectId("123456abcdef123456abcdef"),"d","e","f"));
    public  List<Clients> CLIENTS_LIST = Arrays.asList(CLIENT_TEST,CLIENT_TEST_UPDATE);
    public  List<Reviews> REVIEWS = Arrays.asList(new Reviews(new ObjectId("123456abcdef123456abcdef"),"Pedro", 3, "TEXT"), new Reviews(new ObjectId("123456abcdef123456abcdef"),"Daniel",5,"OTHER TEXT"));
    public  Hotels HOTEL_TEST = new Hotels(new ObjectId("123456abcdef123456abcdef"), "Hotel Las Vegas", ADRESSES, ROOM_TYPES, CLIENTS_LIST, REVIEWS, null);
    public  Hotels HOTEL_TEST_UPDATE = new Hotels(new ObjectId("123456abcdef123456abcde1"), "patata", ADRESSES, ROOM_TYPES, CLIENTS_LIST, REVIEWS, null);
   

    @Test
    void testAddHotels() {
        when(hotelsRepository.existsByName(HOTEL_TEST.getName())).thenReturn(false);
        when(hotelsRepository.save(any(Hotels.class))).thenReturn(HOTEL_TEST);

        Hotels actualHotel = hotelsServiceImp.addHotels(HOTEL_TEST);        
        assertAll(
            () -> assertNotNull(HOTEL_TEST),
            () -> assertEquals(HOTEL_TEST, actualHotel)
            
            );
            verify(hotelsRepository).existsByName(HOTEL_TEST.getName());
            verify(hotelsRepository).save(HOTEL_TEST);
    } 

    @Test
    void testAddHotelsError() {
        when(hotelsRepository.existsByName(HOTEL_TEST.getName())).thenReturn(true);
        Exception exception  = assertThrows(alreadyExistException.class,
         () -> hotelsServiceImp.addHotels(HOTEL_TEST));
        String error = exception.getMessage();
        
        assertAll(
            () -> assertNotNull(HOTEL_TEST),
            () -> assertEquals(error, "OBJECT ALREADY EXISTS")
            
            );

            verify(hotelsRepository).existsByName(HOTEL_TEST.getName());
    } 
  

    @Test
    void testDeleteHotels() {

        when(hotelsRepository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(HOTEL_TEST));
       doNothing().when(hotelsRepository).delete(any(Hotels.class));

       hotelsServiceImp.deleteHotels(new ObjectId("123456abcdef123456abcdef"));

       verify(hotelsRepository).findById(new ObjectId("123456abcdef123456abcdef"));
       verify(hotelsRepository).delete(HOTEL_TEST);

    }

    @Test
    void testGetAllHotels() {
        when(hotelsRepository.findAll()).thenReturn(List.of(HOTEL_TEST, HOTEL_TEST_UPDATE));

        List<Hotels> actualHotel = hotelsServiceImp.getAllHotels();
        
        assertAll(
            () -> assertNotNull(actualHotel),
            () -> assertEquals(List.of(HOTEL_TEST,HOTEL_TEST_UPDATE), actualHotel)
        );

        verify(hotelsRepository).findAll();

    }

    @Test
    void testGetHotels() {

        when(hotelsRepository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(HOTEL_TEST));

        Hotels actualHotel = hotelsServiceImp.getHotels(new ObjectId("123456abcdef123456abcdef"));

        assertAll(
            () -> assertNotNull(actualHotel),
            () -> assertEquals(HOTEL_TEST, actualHotel)
        );

        verify(hotelsRepository).findById(new ObjectId("123456abcdef123456abcdef"));


    }

    @Test
    void testUpdateHotels() {

        when(hotelsRepository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(HOTEL_TEST));
        when(hotelsRepository.save(any(Hotels.class))).thenReturn(HOTEL_TEST_UPDATE);

        Hotels actualHotel = hotelsServiceImp.updateHotels(new ObjectId("123456abcdef123456abcdef"),HOTEL_TEST_UPDATE);

        Exception exception = assertThrows(notFoundException.class,
         () -> hotelsServiceImp.updateHotels(new ObjectId("123456abcdef123456abc123"), HOTEL_TEST_UPDATE));

         String error = exception.getMessage();

        assertAll(
            () -> assertNotNull(actualHotel),
            () -> assertEquals(HOTEL_TEST_UPDATE, actualHotel),
            () -> assertEquals(error, "COULDN'T FIND THE OBJECT")  //
                                
        );

        verify(hotelsRepository).findById(new ObjectId("123456abcdef123456abcdef"));
        verify(hotelsRepository).save(HOTEL_TEST_UPDATE);

    }
}
