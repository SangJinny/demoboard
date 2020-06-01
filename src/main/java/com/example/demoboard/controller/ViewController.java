package com.example.demoboard.controller;

import com.example.demoboard.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping("/new-post")
    public ModelAndView renderPostView() {
        return new ModelAndView("/post");
    }

    @GetMapping("/new-comment")
    public ModelAndView renderCommentView(@RequestParam long postId) {
        ModelAndView modelAndView = new ModelAndView("/comment");
        modelAndView.addObject("postId", postId);
        return modelAndView;
    }
}
