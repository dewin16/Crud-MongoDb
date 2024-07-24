package com.mongodb.mongodb.security.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class createDto {


    private String username;
    private String email;
    private String password;
    private List<String> roles;
    
    


}
