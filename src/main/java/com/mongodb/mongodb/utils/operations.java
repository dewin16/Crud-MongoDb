package com.mongodb.mongodb.utils;

import java.util.Comparator;
import java.util.List;

import com.mongodb.mongodb.nombre.entities.idDocument;

public class operations {

    
    public static Long autoIncrement(List<? extends idDocument> list){
     if (list.isEmpty()) {
        return 1L;
     }else{
        return list.stream().max(Comparator.comparing(idDocument::getId)).get().getId()+ 1;
     }   
    }
}
