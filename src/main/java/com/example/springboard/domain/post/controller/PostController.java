package com.example.springboard.domain.post.controller;

import com.example.springboard.domain.post.domain.dto.request.PostRequest;
import com.example.springboard.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postBoard(@Valid @RequestBody PostRequest postRequest) {
        postService.postBoard(postRequest);
    }
}
