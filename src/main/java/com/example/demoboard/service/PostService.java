package com.example.demoboard.service;

import com.example.demoboard.exception.DemoBoardException;
import com.example.demoboard.model.Post;
import com.example.demoboard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private CommentService commentService;

    @Autowired
    public PostService(PostRepository postRepository,
                       CommentService commentService) {
        this.postRepository = postRepository;
        this.commentService = commentService;
    }

    public Post getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow( () -> new DemoBoardException("Post is not found"));
        post.setCommentList(commentService.getCommentListByPostId(id));
        return post;
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

    @Transactional
    public void deletePost(long id) {
        postRepository.deleteById(id);
        commentService.deleteAllComment(id);
    }

    public Post updatePost(long id, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(() -> new DemoBoardException("Post is not found"));
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }
}
