package com.example.springboard.domain.post.service;

import com.example.springboard.domain.post.domain.Post;
import com.example.springboard.domain.post.controller.dto.request.PostRequest;
import com.example.springboard.domain.post.controller.dto.request.PostUpdateRequest;
import com.example.springboard.domain.post.controller.dto.response.PostListResponse;
import com.example.springboard.domain.post.domain.repository.PostRepository;
import com.example.springboard.domain.user.domain.User;
import com.example.springboard.domain.user.domain.repository.UserRepository;
import com.example.springboard.domain.post.exception.PostNotFoundException;
import com.example.springboard.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        postRepository.delete(post);
    }

    @Transactional
    public void postUpdate(Long id, PostUpdateRequest postUpdateRequest) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        post.postUpdate(postUpdateRequest.getTitle(),
                postUpdateRequest.getContent());
    }

    @Transactional(readOnly = true)
    public PostListResponse searchAllPostAtUsers(Long userId) {

        User user = getUser(userId);

        List<PostListResponse.PostResponse> postList = postRepository.findAllByUserId(user.getId())
                .stream()
                .map(post -> PostListResponse.PostResponse.builder()
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());

        return new PostListResponse(postList);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }


}
