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

    @PostMapping("/{user-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void postBoard(@PathVariable("user-id") Long userId, @RequestBody @Valid PostRequest postRequest) {
        postService.postBoard(userId, postRequest);
    }

    @DeleteMapping("/{id}")
    public void postDelete(@PathVariable("id") Long id) {
        postService.postDelete(id);
    }

    @PutMapping("/{id}")
    public void postUpdate(@PathVariable("id") Long id, @RequestBody @Valid PostUpdateRequest postUpdateRequest) {
        postService.postUpdate(id, postUpdateRequest);
    }

    @GetMapping("/searchAll/{user-id}")
    public PostListResponse searchAllPostAtUsers(@PathVariable("user-id") Long userId) {
        return postService.searchAllPostAtUsers(userId);
    }
}
