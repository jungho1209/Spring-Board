package com.example.springboard.domain.comment.domain.repository;

import com.example.springboard.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findById(Long commentId);

    @Query("SELECT p FROM Comment p ORDER BY p.id DESC ")
    List<Comment> findAllByOrderByIdDesc(Long postId);
}
