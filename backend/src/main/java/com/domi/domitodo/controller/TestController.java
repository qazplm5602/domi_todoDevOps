package com.domi.domitodo.controller;

import com.domi.domitodo.entity.User;
import com.domi.domitodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {
    final UserService userService;

//    @GetMapping("/test")
//    User getLoginUserId() {
//        User user = userService.getCurrentUser();
//        return user;
//    }
}
