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
        Content content = contentRepository.save(post.getContent());
        post.setContent(content);
        return postRepository.save(post);
    }

    public Post updatePost(Post post) {
        Content content = contentRepository.save(post.getContent());
        post.setContent(content);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
