package com.example.demoboard.controller;

import com.example.demoboard.model.Post;
import com.example.demoboard.service.CommentService;
import com.example.demoboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    private CommentService commentService;

    @Autowired
    public PostController(PostService postService,
                          CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ModelAndView getPost(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("postDetail");
        modelAndView.addObject("post", postService.getPostById(id));
        modelAndView.addObject("commentList", commentService.getCommentListByPostId(id));
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllPosts() {
        ModelAndView modelAndView = new ModelAndView("postList");
        modelAndView.addObject("postList", postService.getAllPosts());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postPost(@RequestParam String title,
                                 @RequestParam String content,
                                 @RequestParam String writer) {
        ModelAndView modelAndView = new ModelAndView("postDetail");
        modelAndView.addObject("post", postService.makePost(writer, title, content));
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletePost(@PathVariable long id) {
        postService.deletePost(id);
        ModelAndView modelAndView = new ModelAndView("postList");
        modelAndView.addObject("postList", postService.getAllPosts());
        return modelAndView;
    }


    @PutMapping("/{id}")
    public ModelAndView putPost(@PathVariable long id,
                                @RequestParam String title,
                                @RequestParam String content) {
        postService.updatePost(id, title, content);
        ModelAndView modelAndView = new ModelAndView("postList");
        modelAndView.addObject("postList", postService.getAllPosts());
        return modelAndView;
    }
}
