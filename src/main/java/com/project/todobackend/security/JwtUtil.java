package com.project.todobackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtUtil {

    private final String secretKey;

    public JwtUtil() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            this.secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        long expirationTimeInMillis = System.currentTimeMillis()
                + TimeUnit.HOURS.toMillis(0)
                + TimeUnit.MINUTES.toMillis(100)
                + TimeUnit.SECONDS.toMillis(0);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(expirationTimeInMillis)) // 10 minutes
                .and()
                .signWith(getKey())
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsernameFromToken(String jwtToken) {
        return getClaims(jwtToken).getSubject();
    }

    public boolean validateToken(String jwtToken) {
        return getClaims(jwtToken).getExpiration().after(new Date());
    }
}
