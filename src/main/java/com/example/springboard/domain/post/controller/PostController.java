package com.example.springboard.domain.post.controller;

import com.example.springboard.domain.post.controller.dto.request.PostRequest;
import com.example.springboard.domain.post.controller.dto.request.PostUpdateRequest;
import com.example.springboard.domain.post.controller.dto.response.PostListResponse;
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
    public void postBoard(@RequestBody @Valid PostRequest postRequest) {
        postService.postBoard(postRequest);
    }

    @DeleteMapping("/{post-id}")
    public void postDelete(@PathVariable("post-id") Long postId) {
        postService.postDelete(postId);
    }

    @PutMapping("/{post-id}")
    public void postUpdate(@PathVariable("post-id") Long postId, @RequestBody @Valid PostUpdateRequest postUpdateRequest) {
        postService.postUpdate(postId, postUpdateRequest);
    }

    @GetMapping
    public PostListResponse searchAllPostAtUsers() {
        return postService.searchAllPostAtUsers();
    }
}
