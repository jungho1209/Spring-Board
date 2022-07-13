package com.example.springboard.domain.user.service;

import com.example.springboard.domain.user.domain.User;
import com.example.springboard.domain.user.domain.dto.request.UserPutRequest;
import com.example.springboard.domain.user.domain.dto.request.UserRequest;
import com.example.springboard.domain.user.domain.repository.UserRepository;
import com.example.springboard.global.exception.AlreadyExistAccountException;
import com.example.springboard.global.exception.IdNotFoundException;
import com.example.springboard.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
                .orElseThrow(() -> IdNotFoundException.EXCEPTION);

        userRepository.delete(user);
    }

    @Transactional
    public void userUpdate(String accountId, UserPutRequest userPutRequest) {

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.userUpdate(passwordEncoder.encode(userPutRequest.getPassword()),
                userPutRequest.getName());
    }


}
