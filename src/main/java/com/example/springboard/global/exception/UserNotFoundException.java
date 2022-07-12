package com.example.springboard.global.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class UserNotFoundException extends CustomException {

    public static final UserNotFoundException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.NOT_EXIST_ACCOUNT);
    }
}
