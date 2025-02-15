package com.domi.domitodo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${domi.jwt.secret}")
    private String SECRET_KEY;
    private SecretKey signKey;

    @PostConstruct
    void init() {
        signKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public Claims getTokenClaims(String token) {
        return Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String createToken(String id, String name, boolean refresh) {
        long now = System.currentTimeMillis();

        int expireAccess = 1000 * 60 * 60;
        int expireRefresh = 1000 * 60 * 60 * 12;

        return Jwts.builder()
                .claim("username", name)
                .subject(id)
                .issuer("domi-todo")
                .issuedAt(new Date(now))
                .expiration(new Date(now + (refresh ? expireRefresh : expireAccess)))
                .signWith(signKey, Jwts.SIG.HS256)
                .compact();
    }
}
