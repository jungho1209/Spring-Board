package com.example.springboard.domain.post.service;

import com.example.springboard.domain.post.domain.Post;
import com.example.springboard.domain.post.domain.dto.request.PostRequest;
import com.example.springboard.domain.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void postBoard(PostRequest postRequest) {

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();
        postRepository.save(post);
    }
}
