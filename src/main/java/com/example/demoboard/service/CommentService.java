package com.example.demoboard.service;

import com.example.demoboard.model.Comment;
import com.example.demoboard.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentListByPostId(long postId) {
        return commentRepository.findAllByPostId(postId);
    }
    public Comment makeComment(String writer, String content, long postId) {
        Comment comment = new Comment();
        comment.setWriter(writer);
        comment.setContent(content);
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }
}
