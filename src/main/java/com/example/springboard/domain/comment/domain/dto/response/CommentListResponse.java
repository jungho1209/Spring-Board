package com.example.springboard.domain.comment.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommentListResponse {

    private final List<CommentResponse> commentList;

    @Getter
    @Builder
    public static class CommentResponse {
        private final Long commentId;
        private final String comment;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
    }


}
