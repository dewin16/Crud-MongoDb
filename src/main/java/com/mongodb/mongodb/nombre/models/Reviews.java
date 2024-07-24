package com.mongodb.mongodb.nombre.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reviews {

    private Long id;
    private String clientName;
    private int score;
    private String text;

}
