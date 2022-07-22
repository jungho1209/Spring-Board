package com.example.springboard.domain.comment.controller;

import com.example.springboard.domain.comment.controller.dto.request.CreateCommentRequest;
import com.example.springboard.domain.comment.controller.dto.request.UpdateCommentRequest;
import com.example.springboard.domain.comment.controller.dto.response.CommentListResponse;
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
    @PostMapping("/{post-id}")
    public void createComment(@PathVariable("post-id") Long postId,
                              @RequestBody @Valid CreateCommentRequest request) {
        commentService.createComment(postId, request.getComment());
    }

    @DeleteMapping("/{comment-id}")
    public void deleteComment(@PathVariable("comment-id") Long commentId) {
        commentService.deleteComment(commentId);
    }

    @PutMapping("/{comment-id}")
    public void updateComment(@PathVariable("comment-id") Long commentId,
                              @RequestBody @Valid UpdateCommentRequest request) {
        commentService.updateComment(commentId, request.getComment());
    }

    @GetMapping("/{post-id}")
    public CommentListResponse getCommentList(@PathVariable("post-id") Long postId) {
        return commentService.getCommentList(postId);
    }
}
