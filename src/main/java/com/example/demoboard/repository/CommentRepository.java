package com.example.demoboard.repository;

import com.example.demoboard.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(long postId);
    void deleteAllByPostId(long postId);
}
