package com.example.springboard.domain.user.service;

import com.example.springboard.domain.user.controller.dto.request.UserLoginRequest;
import com.example.springboard.domain.user.controller.dto.request.UserPutRequest;
import com.example.springboard.domain.user.controller.dto.request.UserRequest;
import com.example.springboard.domain.user.controller.dto.response.TokenResponse;
import com.example.springboard.domain.user.domain.User;
import com.example.springboard.domain.user.domain.repository.UserRepository;
import com.example.springboard.domain.user.exception.AlreadyExistAccountException;
import com.example.springboard.domain.user.exception.PasswordMissMatchException;
import com.example.springboard.domain.user.exception.UserNotFoundException;
import com.example.springboard.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void userSignUp(UserRequest userRequest) {
        if (userRepository.findByAccountId(userRequest.getAccountId()).isPresent()) {
            throw AlreadyExistAccountException.EXCEPTION;
        }

        User user = User.builder()
                .accountId(userRequest.getAccountId())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void userDelete(String accountId) {

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        userRepository.delete(user);
    }

    @Transactional
    public void userUpdate(String accountId, UserPutRequest userPutRequest) {

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.userUpdate(passwordEncoder.encode(userPutRequest.getPassword()),
                userPutRequest.getName());
    }

    public TokenResponse userSignIn(UserLoginRequest userLoginRequest) {

        User user = userRepository.findByAccountId(userLoginRequest.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw PasswordMissMatchException.EXCEPTION;
        }

        TokenResponse tokenResponse = jwtTokenProvider.generateTokens(userLoginRequest.getAccountId());

        return TokenResponse.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }
}
