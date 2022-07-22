package com.example.springboard.domain.post.service;

import com.example.springboard.domain.post.controller.dto.request.PostRequest;
import com.example.springboard.domain.post.controller.dto.request.PostUpdateRequest;
import com.example.springboard.domain.post.controller.dto.response.PostListResponse;
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
public class PostService {

    private final PostRepository postRepository;
    private final CurrentFacade currentFacade;

    @Transactional
    public void postBoard(PostRequest postRequest) {
        User user = currentFacade.getCurrentUser();

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .user(user)
                .build();
        postRepository.save(post);
    }

    @Transactional
    public void postDelete(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        postRepository.delete(post);
    }

    @Transactional
    public void postUpdate(Long postId, PostUpdateRequest postUpdateRequest) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        post.postUpdate(postUpdateRequest.getTitle(),
                postUpdateRequest.getContent());
    }

    @Transactional(readOnly = true)
    public PostListResponse searchAllPostAtUsers() {

        User user = currentFacade.getCurrentUser();

        List<PostListResponse.PostResponse> postList = postRepository.findAllByUserId(user.getId())
                .stream()
                .map(post -> PostListResponse.PostResponse.builder()
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());

        return new PostListResponse(postList);
    }

}
