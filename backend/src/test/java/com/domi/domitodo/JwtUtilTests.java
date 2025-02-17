package com.domi.domitodo;

import com.domi.domitodo.VO.UserTokenVO;
import com.domi.domitodo.exception.TokenException;
import com.domi.domitodo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    @DisplayName("Claim 오류 체크")
    void parseClaim2() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3Mzk2OTM2MzgsImV4cCI6MTUzOTY5NTQzOH0.N-mQrDxHGHBad-OOBFJa8EtsqLLxFF_Yx_QiskiUCMlV6chHj1qlFYRH2lZ9DnUSYH915StCGJa6AdIJkGjI4w";
        assertThrows(TokenException.class, () -> jwtUtil.getTokenClaims(token));
    }

    @Test
    @DisplayName("토큰 파싱")
    void parseToken() {
        String accessToken = jwtUtil.createToken("domi", "domidami", false);
        System.out.println(accessToken);

        UserTokenVO result = jwtUtil.parseToken(accessToken);
        assertEquals("domi", result.getUserId());
        assertEquals("domidami", result.getName());
        assertFalse(result.isRefresh());
    }
}
