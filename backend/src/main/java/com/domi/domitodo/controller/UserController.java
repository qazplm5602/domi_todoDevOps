package com.domi.domitodo.controller;

import com.domi.domitodo.VO.UserVO;
import com.domi.domitodo.entity.User;
import com.domi.domitodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    final UserService userService;

    @GetMapping("/@me")
    ResponseEntity<UserVO> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(UserVO.toEntity(user));
    }
}
