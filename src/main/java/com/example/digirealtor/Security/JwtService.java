package com.example.digirealtor.Security;

import java.util.Date;
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

    static final String SECRETKEY = "27A7F28FACC32F81B9C93B583C2C3B648374D8786E7D4BBE69FB3CF6055F35827A39347304F9D7124F74FFD8B23AA6C1EBCEE63463803B53EE5244EB0D2D0F45";

    // extract username
    public String extractUsername(String token) {
        return extractSingleClaim(token, Claims::getSubject);
    }

    /// extract singleClaim
    private <T> T extractSingleClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // extractAllClaims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRETKEY)
                .parseClaimsJwt(token)
                .getBody();
    }
    // generate token without passing claims

    public String buildToken(UserDetails userDetails) {
        return buildToken(userDetails, new HashMap<>());
    }

    // generate token
    public String buildToken(UserDetails userDetails, Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)
                .compact();
    }

    // check if toke n is valid
    public boolean isTokenValid(UserDetails userDetails, String token) {

        String username = extractUsername(token);
        return (userDetails.getUsername().equals(username) && !tokenExpired(token));
    }

    // check if token is expired
    private boolean tokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }
    // extract expiration date of a token

    private Date extractExpiration(String token) {
        return extractSingleClaim(token, Claims::getExpiration);
    }

}
