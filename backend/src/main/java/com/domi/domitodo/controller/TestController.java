package com.domi.domitodo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    Authentication getLoginUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }
}
