package com.domi.domitodo.service;

import com.domi.domitodo.VO.CustomUserDetails;
import com.domi.domitodo.entity.User;
import com.domi.domitodo.exception.UserException;
import com.domi.domitodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;

    public Authentication getUserAuthenticationByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException(UserException.Type.NOT_FOUND_USER));
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }
}
