package com.example.springboard.domain.post.service;

import com.example.springboard.domain.post.domain.Post;
import com.example.springboard.domain.post.domain.dto.request.PostRequest;
import com.example.springboard.domain.post.domain.dto.request.PostUpdateRequest;
import com.example.springboard.domain.post.domain.repository.PostRepository;
import com.example.springboard.domain.user.domain.User;
import com.example.springboard.domain.user.domain.repository.UserRepository;
import com.example.springboard.global.exception.IdNotFoundException;
import com.example.springboard.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void postBoard(Long userId, PostRequest postRequest) {

        User user = getUser(userId);

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .user(user)
                .build();
        postRepository.save(post);
    }

    @Transactional
    public void postDelete(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> IdNotFoundException.EXCEPTION);

        postRepository.delete(post);
    }

    @Transactional
    public void postUpdate(Long id, PostUpdateRequest postUpdateRequest) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> IdNotFoundException.EXCEPTION);

        post.postUpdate(postUpdateRequest.getTitle(),
                postUpdateRequest.getContent());
    }


    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }


}
