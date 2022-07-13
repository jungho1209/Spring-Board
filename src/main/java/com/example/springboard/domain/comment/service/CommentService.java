package com.example.springboard.domain.comment.service;

import com.example.springboard.domain.comment.domain.Comment;
import com.example.springboard.domain.comment.domain.dto.response.CommentListResponse;
import com.example.springboard.domain.comment.domain.dto.response.CommentResponse;
import com.example.springboard.domain.comment.domain.repository.CommentRepository;
import com.example.springboard.domain.post.domain.Post;
import com.example.springboard.domain.post.domain.repository.PostRepository;
import com.example.springboard.domain.user.domain.User;
import com.example.springboard.domain.user.domain.repository.UserRepository;
import com.example.springboard.global.exception.*;
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
    private final UserRepository userRepository;

    @Transactional
    public void createComment(Long userId, Long postId, String comment) {

        User user = getUser(userId);
        Post post = getPost(postId);

        commentRepository.save(Comment.builder()
                .comment(comment)
                .post(post)
                .user(user)
                .build());
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {

        User user = getUser(userId);
        Comment comment = getComment(commentId);

        if (!comment.getUser().equals(user)) {
            throw NoPermissionToDeleteCommentException.EXCEPTION;
        }

        commentRepository.delete(comment);
    }

    @Transactional
    public void updateComment(Long commentId, Long userId, String comment) {

        User user = getUser(userId);
        Comment dbComment = getComment(commentId);

        if (!dbComment.getUser().equals(user)) {
            throw NoPermissionToModifyCommentException.EXCEPTION;
        }

        dbComment.updateComment(comment);
    }

    @Transactional(readOnly = true)
    public CommentListResponse getCommentList(Long postId) {

        Post post = getPost(postId);

        List<CommentListResponse.CommentResponse> commentList = commentRepository.findAllByPostId(post.getId())
                .stream()
                .map(comment -> CommentListResponse.CommentResponse.builder()
                        .commentId(comment.getId())
                        .comment(comment.getComment())
                        .build())
                .collect(Collectors.toList());

        return new CommentListResponse(commentList);
    }


    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }
}
