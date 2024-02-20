package com.fredrikkodar.blogwebservice.service;

import com.fredrikkodar.blogwebservice.models.Content;
import com.fredrikkodar.blogwebservice.models.Post;
import com.fredrikkodar.blogwebservice.repository.ContentRepository;
import com.fredrikkodar.blogwebservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ContentRepository contentRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        Content savedContent = contentRepository.save(post.getContent());
        post.setContent(savedContent);
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post with id " + id + " does not exist"));

        if (postDetails.getTitle() != null) {
            post.setTitle(postDetails.getTitle());
        }
        if (postDetails.getContent() != null) {
            post.getContent().setText(postDetails.getContent().getText());
        }
        if (postDetails.getCategory() != null) {
            post.setCategory(postDetails.getCategory());
        }

        return postRepository.save(post);
    }
    public void deletePost(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Post with id " + id + " does not exist");
        }
    }
}