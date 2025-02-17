package com.domi.domitodo.util;

import com.domi.domitodo.VO.UserTokenVO;
import com.domi.domitodo.exception.TokenException;
import io.jsonwebtoken.*;
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
        Claims claims;

        try {
            claims = Jwts.parser()
                    .verifyWith(signKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new TokenException(TokenException.Type.EXPIRE_TOKEN);
        } catch (SecurityException e) {
            throw new TokenException(TokenException.Type.WRONG_SIGN);
        }

        return claims;
    }

    public String createToken(String id, String name, boolean refresh) {
        long now = System.currentTimeMillis();

        int expireAccess = 1000 * 60 * 60;
        int expireRefresh = 1000 * 60 * 60 * 12;

        return Jwts.builder()
                .id(id)
                .subject(name)
                .issuer("domi-todo")
                .issuedAt(new Date(now))
                .expiration(new Date(now + (refresh ? expireRefresh : expireAccess)))
                .claim("refresh", refresh)
                .signWith(signKey, Jwts.SIG.HS256)
                .compact();
    }

    public UserTokenVO parseToken(String token) {
        Claims claims = getTokenClaims(token);
        boolean isRefresh = (boolean)claims.get("refresh");

        return new UserTokenVO(claims.getId(), claims.getSubject(), isRefresh);
    }
}
