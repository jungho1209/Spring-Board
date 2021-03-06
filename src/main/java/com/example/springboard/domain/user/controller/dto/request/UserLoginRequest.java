package com.example.springboard.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserLoginRequest {

    @NotBlank
    @Size(min = 8, max = 20, message = "account_id 는 8자~20자이며, 공백, Null 이 불가합니다.")
    private String accountId;

    @NotBlank
    @Size(min = 8, max = 60, message = "password 는 8자~60자이며, 공백, Null 이 불가합니다.")
    private String password;

}
