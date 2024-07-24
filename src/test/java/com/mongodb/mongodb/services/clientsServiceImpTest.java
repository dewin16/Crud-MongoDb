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
import com.mongodb.mongodb.exceptions.notFoundException;
import com.mongodb.mongodb.nombre.entities.Clients;
import com.mongodb.mongodb.nombre.repositories.clientsRepository;
import com.mongodb.mongodb.nombre.services.clientsServiceImp;

@ExtendWith(MockitoExtension.class)
public class clientsServiceImpTest {

    @Mock
    private clientsRepository clientsRepository;

    @InjectMocks
    private clientsServiceImp clientsServiceImp;

   

    @Test
    void testAddClients() {
      
        when(clientsRepository.existsByName(AppConstants.CLIENT_TEST.getName())).thenReturn(false);
        when(clientsRepository.save(AppConstants.CLIENT_TEST)).thenReturn(AppConstants.CLIENT_TEST);
        Clients clientSaved = clientsServiceImp.addClients(AppConstants.CLIENT_TEST);
        
        assertAll(
            () -> assertNotNull(clientSaved),
            () -> assertEquals(AppConstants.CLIENT_TEST.getId(), clientSaved.getId(), "Diff id"),
            () -> assertEquals(AppConstants.CLIENT_TEST.getMail(), clientSaved.getMail(), "Diff mail"),
            () -> assertEquals(AppConstants.CLIENT_TEST.getName(), clientSaved.getName(), "Diff name"),
            () -> assertEquals(AppConstants.CLIENT_TEST.getPhoneNumber(), clientSaved.getPhoneNumber(),
                    "Diff number"));
                    
        verify(clientsRepository).existsByName(AppConstants.CLIENT_TEST.getName());
         verify(clientsRepository).save(clientSaved);


    }

    @Test
    void testDeleteClients() {

        when(clientsRepository.findById(1L)).thenReturn(Optional.of(AppConstants.CLIENT_TEST));
        doNothing().when(clientsRepository).delete(any(Clients.class));


        clientsServiceImp.deleteClients(1L);

        verify(clientsRepository).delete(AppConstants.CLIENT_TEST);
        verify(clientsRepository).findById(anyLong());

    }

    @Test
    void testGetAllClients() {

        when(clientsRepository.findAll()).thenReturn(List.of(AppConstants.CLIENT_TEST));

        List<Clients> actualClient = clientsServiceImp.getAllClients();

        assertAll(
            () -> assertNotNull(actualClient),
            () -> assertEquals(List.of(AppConstants.CLIENT_TEST), actualClient)
        );

        verify(clientsRepository).findAll();

    }

    @Test
    void testGetClient() {

        when(clientsRepository.findById(1L)).thenReturn(Optional.of(AppConstants.CLIENT_TEST));

        Clients actualClient = clientsServiceImp.getClient(1L);

        assertAll(
            () -> assertNotNull(actualClient),
            () -> assertEquals(AppConstants.CLIENT_TEST, actualClient)

        );

        verify(clientsRepository).findById(1L);
    }

    @Test
    void testUpdateClients() {

        when(clientsRepository.findById(1L)).thenReturn(Optional.of(AppConstants.CLIENT_TEST));
        when(clientsRepository.save(AppConstants.CLIENT_TEST_UPDATE)).thenReturn(AppConstants.CLIENT_TEST_UPDATE);
    
        Clients client = clientsServiceImp.updateClients(1L, AppConstants.CLIENT_TEST_UPDATE);

        Exception probando = assertThrows(notFoundException.class,
         () -> clientsServiceImp.updateClients(15L, client));
        String error = probando.getMessage();

        assertAll(
            () -> assertNotNull(client), 
            () -> assertEquals(AppConstants.CLIENT_TEST_UPDATE.getId(), client.getId(), () -> "ERROR: Different objects"),
            () -> assertEquals("Client wasnt found",error )
            
           
            );
            
        verify(clientsRepository).findById(1L);
        verify(clientsRepository).save(AppConstants.CLIENT_TEST_UPDATE);
    }
}
