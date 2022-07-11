package com.example.springboard.global.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class IdNotFoundException extends CustomException {

    public static final IdNotFoundException EXCEPTION =
            new IdNotFoundException();

    private IdNotFoundException() {
        super(ErrorCode.ID_NOT_FOUND);
    }
}
