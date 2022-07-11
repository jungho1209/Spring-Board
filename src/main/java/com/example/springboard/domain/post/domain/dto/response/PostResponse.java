package com.example.springboard.domain.post.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

    private final String title;
    private final String content;
}
