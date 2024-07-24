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
import com.mongodb.mongodb.nombre.entities.Hotels;
import com.mongodb.mongodb.nombre.repositories.hotelsRepository;
import com.mongodb.mongodb.nombre.services.hotelsServiceImp;

@ExtendWith(MockitoExtension.class)
public class hotelsServiceImpTest {

    @Mock
    private hotelsRepository hotelsRepository;

    @InjectMocks
    private hotelsServiceImp hotelsServiceImp;

    @Test
    void testAddHotels() {
        when(hotelsRepository.existsByName(AppConstants.HOTEL_TEST.getName())).thenReturn(false);
        when(hotelsRepository.save(any(Hotels.class))).thenReturn(AppConstants.HOTEL_TEST);

        Hotels actualHotel = hotelsServiceImp.addHotels(AppConstants.HOTEL_TEST);        
        assertAll(
            () -> assertNotNull(AppConstants.HOTEL_TEST),
            () -> assertEquals(AppConstants.HOTEL_TEST, actualHotel)
            
            );
            verify(hotelsRepository).existsByName(AppConstants.HOTEL_TEST.getName());
            verify(hotelsRepository).save(AppConstants.HOTEL_TEST);
    } 

    @Test
    void testAddHotelsError() {
        when(hotelsRepository.existsByName(AppConstants.HOTEL_TEST.getName())).thenReturn(true);
        Exception exception  = assertThrows(alreadyExistException.class,
         () -> hotelsServiceImp.addHotels(AppConstants.HOTEL_TEST));
        String error = exception.getMessage();
        
        assertAll(
            () -> assertNotNull(AppConstants.HOTEL_TEST),
            () -> assertEquals(error, "OBJECT ALREADY EXISTS")
            
            );

            verify(hotelsRepository).existsByName(AppConstants.HOTEL_TEST.getName());
    } 
  

    @Test
    void testDeleteHotels() {

        when(hotelsRepository.findById(anyLong())).thenReturn(Optional.of(AppConstants.HOTEL_TEST));
       doNothing().when(hotelsRepository).delete(any(Hotels.class));

       hotelsServiceImp.deleteHotels(anyLong());

       verify(hotelsRepository).findById(anyLong());
       verify(hotelsRepository).delete(AppConstants.HOTEL_TEST);

    }

    @Test
    void testGetAllHotels() {
        when(hotelsRepository.findAll()).thenReturn(List.of(AppConstants.HOTEL_TEST, AppConstants.HOTEL_TEST_UPDATE));

        List<Hotels> actualHotel = hotelsServiceImp.getAllHotels();
        
        assertAll(
            () -> assertNotNull(actualHotel),
            () -> assertEquals(List.of(AppConstants.HOTEL_TEST,AppConstants.HOTEL_TEST_UPDATE), actualHotel)
        );

        verify(hotelsRepository).findAll();

    }

    @Test
    void testGetHotels() {

        when(hotelsRepository.findById(anyLong())).thenReturn(Optional.of(AppConstants.HOTEL_TEST));

        Hotels actualHotel = hotelsServiceImp.getHotels(anyLong());

        assertAll(
            () -> assertNotNull(actualHotel),
            () -> assertEquals(AppConstants.HOTEL_TEST, actualHotel)
        );

        verify(hotelsRepository).findById(anyLong());


    }

    @Test
    void testUpdateHotels() {

        when(hotelsRepository.findById(1L)).thenReturn(Optional.of(AppConstants.HOTEL_TEST));
        when(hotelsRepository.save(any(Hotels.class))).thenReturn(AppConstants.HOTEL_TEST_UPDATE);

        Hotels actualHotel = hotelsServiceImp.updateHotels(1L, AppConstants.HOTEL_TEST_UPDATE);

        Exception exception = assertThrows(notFoundException.class,
         () -> hotelsServiceImp.updateHotels(15L, AppConstants.HOTEL_TEST_UPDATE));

         String error = exception.getMessage();

        assertAll(
            () -> assertNotNull(actualHotel),
            () -> assertEquals(AppConstants.HOTEL_TEST_UPDATE, actualHotel),
            () -> assertEquals(error, "COULDN'T FIND THE OBJECT")  //
                                
        );

        verify(hotelsRepository).findById(1L);
        verify(hotelsRepository).save(AppConstants.HOTEL_TEST_UPDATE);

    }
}
