package com.mongodb.mongodb.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtFilter extends OncePerRequestFilter {

        @Autowired
        private jwtService provider;

        @Autowired 
        private UserDetailsService service;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                FilterChain filterChain) throws ServletException, IOException {
           
            final String  token = getToken(request);
            final String username;
            if(token == null){
                filterChain.doFilter(request, response);
                return;
            }
            username =  provider.getUsernameFromToken(token);
            if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){

                    UserDetails user = service.loadUserByUsername(username);
                    if(provider.validateToken(token, user)){
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,null, user.getAuthorities());

                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request) );

                            SecurityContextHolder.getContext().setAuthentication(authToken);
                    
                }


            }
            filterChain.doFilter(request, response);
        }

        private String getToken(HttpServletRequest request) {
           String header = request.getHeader(HttpHeaders.AUTHORIZATION);
           if(header != null && header.startsWith("Bearer ")){
           return header.substring(7);
           }else{
            return null;
           }
        }


        


}
