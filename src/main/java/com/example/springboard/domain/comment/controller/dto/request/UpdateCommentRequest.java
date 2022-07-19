package com.example.springboard.domain.comment.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

    @NotNull
    @Size(min = 1, max = 500, message = "comment 는 500글자를 넘을 수 없습니다.")
    private String comment;
}
