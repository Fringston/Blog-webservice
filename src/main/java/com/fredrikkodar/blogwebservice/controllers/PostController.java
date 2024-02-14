package com.fredrikkodar.blogwebservice.controllers;

import com.fredrikkodar.blogwebservice.models.Post;
import com.fredrikkodar.blogwebservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        return ResponseEntity.ok(newPost);
    }

    @PatchMapping("")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        Post updatedPost = postService.updatePost(post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
