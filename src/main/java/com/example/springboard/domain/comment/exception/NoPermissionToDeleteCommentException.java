package com.example.springboard.domain.comment.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class NoPermissionToDeleteCommentException extends CustomException {

    public static final NoPermissionToDeleteCommentException EXCEPTION =
            new NoPermissionToDeleteCommentException();

    private NoPermissionToDeleteCommentException() {
        super(ErrorCode.NO_PERMISSION_TO_DELETE_COMMENT);
    }
}
