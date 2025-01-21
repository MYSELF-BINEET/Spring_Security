package com.security.springSecurity.services;

import com.security.springSecurity.entites.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface JwtService {

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token,UserDetails userDetails);

    public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);

}
