package com.example.springboard.domain.user.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPutResponse {

    private final String password;
    private final String name;
}
