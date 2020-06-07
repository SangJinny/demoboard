package com.example.demoboard.controller;

import com.example.demoboard.model.Comment;
import com.example.demoboard.model.Post;
import com.example.demoboard.service.CommentService;
import com.example.demoboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getAllComments(@PathVariable(name = "id") long postId) {
        return commentService.getCommentListByPostId(postId);
    }

    @PostMapping("/posts/{id}/comments")
    public Comment postComment(@PathVariable(name = "id") long postId,
                       @RequestBody Comment comment) {
        return commentService.makeComment(comment.getWriter(), comment.getContent(), postId);
    }

}
