package com.domi.domitodo;

import com.domi.domitodo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class JwtUtilTests {
    @Autowired
    JwtUtil jwtUtil;

    @Test
    @DisplayName("토큰 생성")
    void createToken() {
        String accessToken = jwtUtil.createToken("domi", "도미", false);
        assertNotNull(accessToken);
    }

    @Test
    @DisplayName("Claim 파싱")
    void parseClaim() {
        String accessToken = jwtUtil.createToken("domi", "도미", false);

        Claims claims = jwtUtil.getTokenClaims(accessToken);
        assertNotNull(claims);
        assertEquals("domi", claims.getId());
    }
}
