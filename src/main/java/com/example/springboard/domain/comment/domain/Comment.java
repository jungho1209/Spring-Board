package com.example.springboard.domain.comment.domain;

import com.example.springboard.domain.post.domain.Post;
import com.example.springboard.domain.user.domain.User;
import com.example.springboard.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String comment;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Builder
    public Comment(String comment, LocalDateTime updateAt, Post post, User user) {
        this.comment = comment;
        this.updateAt = updateAt;
        this.post = post;
        this.user = user;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }
}
