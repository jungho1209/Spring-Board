package com.example.springboard.global.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class SignatureJwtException extends CustomException {

    public static final SignatureJwtException EXCEPTION =
            new SignatureJwtException();

    private SignatureJwtException() {
        super(ErrorCode.SIGNATURE_JWT);
    }
}
