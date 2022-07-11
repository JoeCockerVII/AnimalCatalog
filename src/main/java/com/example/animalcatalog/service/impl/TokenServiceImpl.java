package com.example.animalcatalog.service.impl;

import com.example.animalcatalog.domain.entity.User;
import com.example.animalcatalog.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * Service to Generate and Validate Token
 * @author ilyin
 * @since 10.07.2022
 */
@Service
public class TokenServiceImpl implements TokenService {

    private final String secretKey = "secretKey";

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getUserName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000000L))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    @Override
    public String extractUsernameAndValidate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        if (claims == null || claims.getSubject() == null) {
            throw new RuntimeException("Wrong");
        }
        return claims.getSubject();
    }
}
