package com.example.springboard.domain.user.controller;

import com.example.springboard.domain.user.domain.dto.request.UserRequest;
import com.example.springboard.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void userSignUp(@Valid @RequestBody UserRequest userRequest) {
        userService.userSignUp(userRequest);
    }
}
