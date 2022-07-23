package com.example.springboard.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    SIGNATURE_JWT(401, "Signature Jwt"),
    EXPIRED_JWT(401, "Expired Jwt"),
    INVALID_JWT(401, "Invalid Jwt"),
    PASSWORD_MIS_MATCH(401, "Password Mis Match"),
    NO_PERMISSION_TO_DELETE_COMMENT(401, "No Permission To Delete Comment"),
    NO_PERMISSION_TO_MODIFY_COMMENT(401, "No Permission To Modify Comment"),
    INVALID_REFRESH_TOKEN(401, "Invalid Refresh Token"),

    USER_NOT_FOUND(404, "User Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "RefreshToken Not Found"),
    AUTH_NOT_FOUND(404, "Auth Not Found"),
    POST_NOT_FOUND(404, "Post Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),
    ID_NOT_FOUND(404, "Id Mis Match"),
    ACCOUNTID_NOT_FOUND(404, "AccountId Mis Match"),

    USER_EXISTS(409, "User Exists"),
    NOT_EXIST_ACCOUNT(409, "Account Not Exist"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;
}
