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
    private PostService postService;

    @Autowired
    public CommentController(CommentService commentService,
                             PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/posts/{id}/comments")
    public ModelAndView postComment(@PathVariable(name = "id") long postId,
                       @RequestParam String writer,
                       @RequestParam String content) {
        commentService.makeComment(writer, content, postId);

        ModelAndView modelAndView = new ModelAndView("postDetail");
        modelAndView.addObject("post", postService.getPostById(postId));
        modelAndView.addObject("commentList", commentService.getCommentListByPostId(postId));
        return modelAndView;
    }

}
