package com.example.springboard.domain.post.controller;

import com.example.springboard.domain.post.domain.dto.request.PostRequest;
import com.example.springboard.domain.post.domain.dto.request.PostUpdateRequest;
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
    public void postBoard(@PathVariable ("user-id") Long userId, @Valid @RequestBody PostRequest postRequest) {
        postService.postBoard(userId, postRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void postDelete(@PathVariable("id") Long id) {
        postService.postDelete(id);
    }

    @PutMapping("/update/{id}")
    public void postUpdate(@PathVariable ("id") Long id , @Valid @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.postUpdate(id, postUpdateRequest);
    }
}
