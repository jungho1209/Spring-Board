package com.example.springboard.domain.user.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class AlreadyExistAccountException extends CustomException {

    public static final AlreadyExistAccountException EXCEPTION =
            new AlreadyExistAccountException();

    private AlreadyExistAccountException() {
        super(ErrorCode.USER_EXISTS);
    }
}
