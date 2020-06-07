package com.example.demoboard.controller;

import com.example.demoboard.model.Post;
import com.example.demoboard.service.CommentService;
import com.example.demoboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
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
    public Post getPost(@PathVariable long id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public Map<String, List<Post>> getAllPosts() {
        Map<String, List<Post>> response = new HashMap<>();
        response.put("postList", postService.getAllPosts());
        return response;
    }

    @PostMapping
    public Post postPost(@RequestBody Post post) {
        return postService.makePost(post.getWriter(), post.getTitle(), post.getContent());
    }

    @DeleteMapping("/{id}")
    public long deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return id;
    }


    @PutMapping("/{id}")
    public Post putPost(@PathVariable long id,
                        @RequestBody Post post) {
        return postService.updatePost(id, post.getTitle(), post.getContent());
    }
}
