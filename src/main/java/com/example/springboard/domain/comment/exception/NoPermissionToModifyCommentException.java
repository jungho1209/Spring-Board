package com.example.springboard.domain.comment.exception;

import com.example.springboard.global.error.CustomException;
import com.example.springboard.global.error.ErrorCode;

public class NoPermissionToModifyCommentException extends CustomException {

    public static final NoPermissionToModifyCommentException EXCEPTION =
            new NoPermissionToModifyCommentException();

    private NoPermissionToModifyCommentException() {
        super(ErrorCode.NO_PERMISSION_TO_MODIFY_COMMENT);
    }
}
