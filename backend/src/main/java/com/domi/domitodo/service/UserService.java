package com.domi.domitodo.service;

import com.domi.domitodo.VO.CustomUserDetails;
import com.domi.domitodo.entity.User;
import com.domi.domitodo.exception.UserException;
import com.domi.domitodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
