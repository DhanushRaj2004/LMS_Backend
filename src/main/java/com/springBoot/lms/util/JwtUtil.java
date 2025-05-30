package com.springBoot.lms.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    /*
    * In this class we need to create following methods
    * 1.Create a method to generate JWT token
    * 2.Create a method to verify JWT token
    *
    * */

    private static final String secretKey = "LMS_SPRINGBOOT_98876754535398776889";
    private static final long expirationTimeInMills=43200000; //12 hrs

    Key key;

    {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    /* Method to Generate JWT Token*/
    public String createToken(String email){
        System.out.println("I am in create token method");
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMills))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    /* Method to verify the Token */
    public boolean verifyToken(String token, String email) {
        String extractedEmail = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getExpiration();

        return extractedEmail.equals(email) && new Date().before(expirationDate);
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }
}
