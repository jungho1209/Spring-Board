package com.example.springboard.domain.comment.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {

    private final Long commentId;
    private final String comment;

}
