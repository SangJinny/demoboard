package com.example.demoboard.controller;

import com.example.demoboard.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @GetMapping("/new-post")
    public ModelAndView renderPostView() {
        ModelAndView modelAndView = new ModelAndView("/post");
        modelAndView.addObject("post", new Post());
        return modelAndView;
    }
}
