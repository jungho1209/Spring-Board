package com.example.springboard.domain.post.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostRequest {

    @Size(max = 30 , message = "title 은 최대 30자여야 합니다.")
    private String title;

    @Size(max = 1000 , message = "content 는 최대 1000자여야 합니다.")
    private String content;
}
