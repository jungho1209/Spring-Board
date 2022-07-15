package com.example.springboard.global.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class InvalidJwtException extends CustomException {

    public static final InvalidJwtException EXCEPTION =
            new InvalidJwtException();

    private InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}
