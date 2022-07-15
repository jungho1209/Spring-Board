package com.example.springboard.domain.user.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class PasswordMissMatchException extends CustomException {

    public static final PasswordMissMatchException EXCEPTION =
            new PasswordMissMatchException();

    private PasswordMissMatchException() {
        super(ErrorCode.PASSWORD_MIS_MATCH);
    }
}
