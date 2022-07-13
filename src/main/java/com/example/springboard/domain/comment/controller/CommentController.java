package com.example.springboard.domain.comment.controller;

import com.example.springboard.domain.comment.domain.dto.request.CreateCommentRequest;
import com.example.springboard.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{post-id}/{user-id}")
    public void createComment(@PathVariable("post-id") Long postId, @PathVariable ("user-id") Long userId,
                              @RequestBody @Valid CreateCommentRequest request) {
        commentService.createComment(userId, postId, request.getComment());
    }

    @DeleteMapping("/{comment-id}/{user-id}")
    public void deleteComment(@PathVariable("comment-id") Long commentId, @PathVariable ("user-id") Long userId) {
        commentService.deleteComment(commentId, userId);
    }
}
