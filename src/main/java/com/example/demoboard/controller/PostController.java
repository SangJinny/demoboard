package com.example.demoboard.controller;

import com.example.demoboard.model.Post;
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

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ModelAndView getPost(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("postDetail");
        modelAndView.addObject("post", postService.getPostById(id));
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
        ModelAndView modelAndView = new ModelAndView("postList");
        postService.deletePost(id);
        return modelAndView;
    }


    @PutMapping("/{id}")
    public ModelAndView putPost(@PathVariable long id,
                                @RequestParam String title,
                                @RequestParam String content) {
        ModelAndView modelAndView = new ModelAndView("postList");
        postService.updatePost(id, title, content);
        return modelAndView;
    }
}
