package com.example.springboard.domain.user.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class InvalidRefreshTokenException extends CustomException {

    public static final InvalidRefreshTokenException EXCEPTION =
            new InvalidRefreshTokenException();

    private InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
