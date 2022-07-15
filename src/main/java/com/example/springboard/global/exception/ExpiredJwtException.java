package com.example.springboard.global.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class ExpiredJwtException extends CustomException {

    public static final ExpiredJwtException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
