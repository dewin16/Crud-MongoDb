package com.mongodb.mongodb.security.entities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mongodb.mongodb.security.enums.rolesEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "authUser")
public class authUser  implements UserDetails {
   
    @Id
    private ObjectId id;
    private String username;
    private String email;
    private String password;
    private List<rolesEnum> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
      //  return List.of(new SimpleGrantedAuthority(roles.name()));
        
        
        return roles.stream().map(
            role -> new SimpleGrantedAuthority(role.name())
            ).collect(Collectors.toList());
            
            
    }

    

    public authUser(String username, String email, String password, List<rolesEnum> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }



    @Override
public String getUsername() {
    return this.username;
}

@Override
public String getPassword() {
    return this.password;
}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
       return true;
    }





 

    

}
