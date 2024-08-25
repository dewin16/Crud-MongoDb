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

import com.mongodb.mongodb.Crud.entities.Clients;
import com.mongodb.mongodb.Crud.repositories.clientsRepository;
import com.mongodb.mongodb.Crud.services.clientsServiceImp;
import com.mongodb.mongodb.exceptions.notFoundException;

@ExtendWith(MockitoExtension.class)
public class clientsServiceImpTest {

    @Mock
    private clientsRepository clientsRepository;

    @InjectMocks
    private clientsServiceImp clientsServiceImp;

    public  Clients CLIENT_TEST= new Clients(new ObjectId("123456abcdef123456abcdef"), "Juan","1111","juanpalomo@hotmail.com");
    public  Clients CLIENT_TEST_UPDATE = new Clients(new ObjectId("123456abcdef123456abcdef"), "Pedro","1111","juanpalomo@hotmail.com");
   

    @Test
    void testAddClients() {
      
        when(clientsRepository.existsByName(CLIENT_TEST.getName())).thenReturn(false);
        when(clientsRepository.save(CLIENT_TEST)).thenReturn(CLIENT_TEST);
        Clients clientSaved = clientsServiceImp.addClients(CLIENT_TEST);
        
        assertAll(
            () -> assertNotNull(clientSaved),
            () -> assertEquals(CLIENT_TEST.getId(), clientSaved.getId(), "Diff id"),
            () -> assertEquals(CLIENT_TEST.getMail(), clientSaved.getMail(), "Diff mail"),
            () -> assertEquals(CLIENT_TEST.getName(), clientSaved.getName(), "Diff name"),
            () -> assertEquals(CLIENT_TEST.getPhoneNumber(), clientSaved.getPhoneNumber(),
                    "Diff number"));
                    
        verify(clientsRepository).existsByName(CLIENT_TEST.getName());
         verify(clientsRepository).save(clientSaved);


    }

    @Test
    void testDeleteClients() {

        when(clientsRepository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(CLIENT_TEST));
        doNothing().when(clientsRepository).delete(any(Clients.class));


        clientsServiceImp.deleteClients(new ObjectId("123456abcdef123456abcdef"));

        verify(clientsRepository).delete(CLIENT_TEST);
        verify(clientsRepository).findById(new ObjectId("123456abcdef123456abcdef"));

    }

    @Test
    void testGetAllClients() {

        when(clientsRepository.findAll()).thenReturn(List.of(CLIENT_TEST));

        List<Clients> actualClient = clientsServiceImp.getAllClients();

        assertAll(
            () -> assertNotNull(actualClient),
            () -> assertEquals(List.of(CLIENT_TEST), actualClient)
        );

        verify(clientsRepository).findAll();

    }

    @Test
    void testGetClient() {

        when(clientsRepository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(CLIENT_TEST));

        Clients actualClient = clientsServiceImp.getClient(new ObjectId("123456abcdef123456abcdef"));

        assertAll(
            () -> assertNotNull(actualClient),
            () -> assertEquals(CLIENT_TEST, actualClient)

        );

        verify(clientsRepository).findById(new ObjectId("123456abcdef123456abcdef"));
    }

    @Test
    void testUpdateClients() {

        when(clientsRepository.findById(new ObjectId("123456abcdef123456abcdef"))).thenReturn(Optional.of(CLIENT_TEST));
        when(clientsRepository.save(CLIENT_TEST_UPDATE)).thenReturn(CLIENT_TEST_UPDATE);
    
        Clients client = clientsServiceImp.updateClients(new ObjectId("123456abcdef123456abcdef"), CLIENT_TEST_UPDATE);

        Exception exception = assertThrows(notFoundException.class,
         () -> clientsServiceImp.updateClients(new ObjectId("123456abcdef123456abcde1"), client));
        String error = exception.getMessage();

        assertAll(
            () -> assertNotNull(client), 
            () -> assertEquals(CLIENT_TEST_UPDATE.getId(), client.getId(), () -> "ERROR: Different objects"),
            () -> assertEquals("Client wasnt found",error )
            
           
            );
            
        verify(clientsRepository).findById(new ObjectId("123456abcdef123456abcdef"));
        verify(clientsRepository).save(CLIENT_TEST_UPDATE);
    }
}
