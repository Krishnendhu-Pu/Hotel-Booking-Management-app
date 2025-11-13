package com.example.HotelBookingSystem.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretkey;

    public String generateToken(String email){

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000 * 60 * 60))
                .signWith(SignatureAlgorithm.ES256,secretkey)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parser().setSigningKey(secretkey).parseClaimsJwt(token).getBody().getSubject();
    }
}
