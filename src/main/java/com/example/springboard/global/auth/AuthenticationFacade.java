package com.example.springboard.global.auth;

import com.example.springboard.domain.user.domain.User;
import com.example.springboard.domain.user.domain.repository.UserRepository;
import com.example.springboard.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class AuthenticationFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();

        return userRepository.findByAccountId(authDetails.getUsername())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
