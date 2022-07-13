package com.example.springboard.domain.comment.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {

    @Size(min = 1, max = 500, message = "comment 는 500자를 넘을 수 없습니다.")
    private String comment;

}