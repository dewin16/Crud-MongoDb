package com.mongodb.mongodb.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class jwtService {

    private final String key = "dGVzdGtpZXlzZWFsb3I1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MA";
                

    public String getToken(UserDetails userDetails){

        return createToken (new HashMap<>(), userDetails);
    }
        

    
    public String createToken(HashMap<String, Object> Claims, UserDetails userdetails){
        return Jwts.builder()
        .setSubject(userdetails.getUsername())
        .addClaims(Claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 160000 * 50))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
                
    }

    private Key getKey(){
        byte[] secretBytes = Decoders.BASE64URL.decode(key);
        return Keys.hmacShaKeyFor(secretBytes);
    }



    public String getUsernameFromToken(String token) {
       return getClaim(token, Claims::getSubject);
    }



    public boolean validateToken(String token, UserDetails user) {
        
        String username = getUsernameFromToken(token);
        if(username.equals(user.getUsername())  && !isTokenExpired(token)){
            return true;
        }else{
            return false;
        }
    }
    private Claims getAllClaims(String token)
    {
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}