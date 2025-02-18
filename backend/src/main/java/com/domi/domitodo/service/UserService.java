package com.domi.domitodo.service;

import com.domi.domitodo.DTO.LoginDTO;
import com.domi.domitodo.DTO.RegisterFormDTO;
import com.domi.domitodo.VO.CustomUserDetails;
import com.domi.domitodo.entity.User;
import com.domi.domitodo.exception.DomiException;
import com.domi.domitodo.exception.UserException;
import com.domi.domitodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public Authentication getUserAuthenticationByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException(UserException.Type.NOT_FOUND_USER));
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }

    public User getUserByLoginForm(LoginDTO form) {
        DomiException exception = new UserException(UserException.Type.WRONG_LOGIN_FIELD);

        User user = userRepository.findByEmail(form.getEmail()).orElseThrow(() -> exception);
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword()))
            throw exception;

        return user;
    }

    public void signUpUser(RegisterFormDTO form) {
        if (userRepository.findByEmail(form.getEmail()).isPresent())
            throw new UserException(UserException.Type.EXIST_EMAIL);

        User newUser = new User();
        newUser.setEmail(form.getEmail());
        newUser.setName(form.getUsername());
        newUser.setPassword(passwordEncoder.encode(form.getPassword()));

        userRepository.save(newUser); // db에 넣긱ㄱ
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException(UserException.Type.NOT_FOUND_USER));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userDetails = authentication.getPrincipal();
        if (!authentication.isAuthenticated() || !(userDetails instanceof CustomUserDetails)) {
            throw new UserException(UserException.Type.NEED_LOGIN);
        }

        int id = Integer.parseInt(((CustomUserDetails) userDetails).getUsername());
        return getUserById(id);
    }
}
