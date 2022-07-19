package com.example.springboard.domain.user.controller;

import com.example.springboard.domain.user.controller.dto.request.UserLoginRequest;
import com.example.springboard.domain.user.controller.dto.request.UserPutRequest;
import com.example.springboard.domain.user.controller.dto.request.UserRequest;
import com.example.springboard.domain.user.controller.dto.response.TokenResponse;
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
    public void userSignUp(@RequestBody @Valid UserRequest userRequest) {
        userService.userSignUp(userRequest);
    }

    @DeleteMapping("/{account-id}")
    public void userDelete(@PathVariable("account-id") String accountId) {
        userService.userDelete(accountId);
    }

    @PutMapping("/{account-id}")
    public void userUpdate(@PathVariable("account-id") String accountId,
                           @RequestBody @Valid UserPutRequest userPutRequest) {
        userService.userUpdate(accountId, userPutRequest);
    }

    @PostMapping("/login")
    public TokenResponse userSignIn(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return userService.userSignIn(userLoginRequest);
    }
}
