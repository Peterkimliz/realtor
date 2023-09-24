package com.example.digirealtor.Security;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private String SECRET_STRING = "eyJzdWIiOiJwZXRlcmtpcm9uamk4QGdtYWlsLmNvbSIsImV4cCI6MTY4MjY4MDAwOCwiaWF0IjoxNjgyNjc4NTY4fQ";

    // method to extract username
    public String extractUserName(String token) {
        return extractSingleClaim(token, Claims::getSubject);
    }

    // method to extract singleClaim
    private <T> T extractSingleClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);

    }

    /// method to extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_STRING)
                .parseClaimsJwt(token)
                .getBody();

    }

    // method to check if token is valid
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }


   //method to build token with UserDetails only
   public String buildToken(UserDetails userDetails){
    return buildToken(userDetails, new HashMap<String,Object>());
   }

    // method to build the token
    public String buildToken(UserDetails userDetails, Map<String,Object> claims) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(SignatureAlgorithm.HS256, SECRET_STRING)
         
                .compact();

    }

    // method to check if the token has expired
    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    // method to extract token expiration date
    private java.util.Date extractTokenExpiration(String token) {
        return extractSingleClaim(token, Claims::getExpiration);
    }

}
