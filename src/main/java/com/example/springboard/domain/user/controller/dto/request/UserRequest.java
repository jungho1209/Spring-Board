package com.example.springboard.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    @Size(min = 5 , max = 20 , message = "account_id 는 5자 이상 20자 이하에 공백이 없어야 합니다.")
    private String accountId;

    @NotBlank
    @Size(min = 8, max = 60, message = "password 는 8자 이상 60자 이하에 공백이 없어야 합니다.")
    private String password;

    @NotBlank
    @Size(max = 8, message = "name 은 8자 이하에 공백이 없어야 합니다.")
    private String name;
}
