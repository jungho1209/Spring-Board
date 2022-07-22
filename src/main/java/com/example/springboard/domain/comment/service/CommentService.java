package com.example.springboard.domain.comment.service;

import com.example.springboard.domain.comment.controller.dto.response.CommentListResponse;
import com.example.springboard.domain.comment.domain.Comment;
import com.example.springboard.domain.comment.domain.repository.CommentRepository;
import com.example.springboard.domain.comment.exception.CommentNotFoundException;
import com.example.springboard.domain.comment.exception.NoPermissionToDeleteCommentException;
import com.example.springboard.domain.comment.exception.NoPermissionToModifyCommentException;
import com.example.springboard.domain.post.domain.Post;
import com.example.springboard.domain.post.domain.repository.PostRepository;
import com.example.springboard.domain.post.exception.PostNotFoundException;
import com.example.springboard.domain.user.domain.User;
import com.example.springboard.global.facade.CurrentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CurrentFacade currentFacade;

    @Transactional
    public void createComment(Long postId, String comment) {

        User user = currentFacade.getCurrentUser();
        Post post = getPost(postId);

        commentRepository.save(Comment.builder()
                .comment(comment)
                .post(post)
                .user(user)
                .build());
    }

    @Transactional
    public void deleteComment(Long commentId) {

        Comment comment = getComment(commentId);
        User user = currentFacade.getCurrentUser();

        if (!comment.getUser().equals(user)) {
            throw NoPermissionToDeleteCommentException.EXCEPTION;
        }

        commentRepository.delete(comment);
    }

    @Transactional
    public void updateComment(Long commentId, String comment) {


        Comment dbComment = getComment(commentId);
        User user = currentFacade.getCurrentUser();

        if (!dbComment.getUser().equals(user)) {
            throw NoPermissionToModifyCommentException.EXCEPTION;
        }

        dbComment.updateComment(comment);
    }

    @Transactional(readOnly = true)
    public CommentListResponse getCommentList(Long postId) {

        Post post = getPost(postId);

        List<CommentListResponse.CommentResponse> commentList = commentRepository.findAllByOrderByIdDesc(post.getId())
                .stream()
                .map(comment -> CommentListResponse.CommentResponse.builder()
                        .commentId(comment.getId())
                        .comment(comment.getComment())
                        .createdAt(comment.getCreatedAt())
                        .updatedAt(comment.getUpdateAt())
                        .build())
                .collect(Collectors.toList());

        return new CommentListResponse(commentList);
    }


    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }
}
