package com.example.backend.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private SecretKey secretKey;

    public JwtUtil(@Value("${spring.jwt.secret}")String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUserId(String token){
        return Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("userId", String.class);
    }

    public Long getId(String token){
        return Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", Long.class);
    }
    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public String getCategory(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public String createJwt(String category, String userId, Long id,Long expiredMs){
        Date now = new Date(System.currentTimeMillis());
        Date expirationTime = new Date(System.currentTimeMillis() + expiredMs*1000);
        System.out.println("now = " + now);
        System.out.println("expirationTime = " + expirationTime);

        return Jwts.builder()
                .claim("category", category)
                .claim("id",id)
                .claim("userId",userId)
                .issuedAt(now)
                .expiration(expirationTime)
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshJwt(String userId, Long id){
        Long expiredMs = 24 * 60 * 60 * 1000L;
        Date now = new Date(System.currentTimeMillis());
        Date expirationTime = new Date(System.currentTimeMillis() + expiredMs*1000);
        System.out.println("now = " + now);
        System.out.println("expirationTime = " + expirationTime);

        return Jwts.builder()
                .claim("id",id)
                .claim("userId",userId)
                .issuedAt(now)
                .expiration(expirationTime)
                .signWith(secretKey)
                .compact();
    }
}
