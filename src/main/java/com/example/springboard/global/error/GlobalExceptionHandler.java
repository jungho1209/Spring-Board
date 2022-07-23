package com.example.springboard.global.error;

import com.example.springboard.global.error.ErrorResponse.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> customExceptionHandling(CustomException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(
                CustomErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .messages(errorCode.getMessage())
                        .build(),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindExceptionHandling(BindException e) {
        Map<String, String> errorList = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            errorList.put(error.getField(),
                    error.getDefaultMessage());
        }

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }
}
