package com.example.springboard.domain.user.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class RefreshTokenNotFoundException extends CustomException {

    public static final RefreshTokenNotFoundException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
