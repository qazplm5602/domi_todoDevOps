package com.domi.domitodo.controller;

import com.domi.domitodo.DTO.LoginDTO;
import com.domi.domitodo.DTO.RegisterFormDTO;
import com.domi.domitodo.VO.AuthTokenVO;
import com.domi.domitodo.entity.User;
import com.domi.domitodo.service.UserService;
import com.domi.domitodo.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    final UserService userService;
    final JwtUtil jwtUtil;

    @PostMapping("/login")
    AuthTokenVO loginUser(@RequestBody LoginDTO form) {
        User user = userService.getUserByLoginForm(form);

        String accessToken = jwtUtil.createToken(user.getEmail(), user.getName(), false);
        String refreshToken = jwtUtil.createToken(user.getEmail(), user.getName(), true);

        return new AuthTokenVO(accessToken, refreshToken);
    }

    @PostMapping("/register")
    void registerUser(@RequestBody @Valid RegisterFormDTO form) {
        userService.signUpUser(form);
    }
}
