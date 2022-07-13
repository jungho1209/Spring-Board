package com.example.springboard.global.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class AccountIdNotFoundException extends CustomException {

    public static final AccountIdNotFoundException EXCEPTION =
            new AccountIdNotFoundException();

    private AccountIdNotFoundException() {
        super(ErrorCode.ACCOUNTID_NOT_FOUND);
    }
}
