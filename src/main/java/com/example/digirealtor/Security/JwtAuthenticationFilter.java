package com.example.digirealtor.Security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

       String jwtToken="";
        String username;
        

        //getting the token from the headers 
         String authToken=request.getHeader("Authorization");   

         if(authToken==null|| !authToken.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
         }
         //extract the token from authToken
         jwtToken=authToken.substring(7);

         
        
    }
    
}
