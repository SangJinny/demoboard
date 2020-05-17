package com.example.demoboard.service;

import com.example.demoboard.model.Post;
import com.example.demoboard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post makePost(String writer, String title, String content) {
        Post post = new Post();
        post.setWriter(writer);
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public Post updatePost(long id, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(RuntimeException::new);
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }
}
