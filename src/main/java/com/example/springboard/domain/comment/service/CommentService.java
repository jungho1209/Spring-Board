package com.example.springboard.domain.comment.service;

import com.example.springboard.domain.comment.domain.Comment;
import com.example.springboard.domain.comment.domain.repository.CommentRepository;
import com.example.springboard.domain.post.domain.Post;
import com.example.springboard.domain.post.domain.repository.PostRepository;
import com.example.springboard.domain.user.domain.User;
import com.example.springboard.domain.user.domain.repository.UserRepository;
import com.example.springboard.global.exception.PostNotFoundException;
import com.example.springboard.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
