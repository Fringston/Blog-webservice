package com.fredrikkodar.blogwebservice.servicetests;

import com.fredrikkodar.blogwebservice.models.Content;
import com.fredrikkodar.blogwebservice.models.Post;
import com.fredrikkodar.blogwebservice.models.User;
import com.fredrikkodar.blogwebservice.repository.ContentRepository;
import com.fredrikkodar.blogwebservice.repository.PostRepository;
import com.fredrikkodar.blogwebservice.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostTests {

    @Mock
    private PostRepository postRepository;
    @Mock
    private ContentRepository contentRepository;
    @InjectMocks
    private PostService postService;

    private Post post;
    private Content content;
    private User user;

    @BeforeEach
    public void setUp() {
        content = new Content();
        content.setId(1L);
        content.setText("Test Content");

        user = new User();
        user.setId(1L);
        user.setUsername("Test User");
        user.setPassword("Test Password");
        user.setAge(20);

        post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setContent(content);
        post.setUser(user);
        post.setDate("2021-09-01");
        post.setCategory("Test Category");
    }

    @Test
    void getAllPosts() {
        // Skapa en lite med alla Post-objekt
        List<Post> posts = new ArrayList<>();
        posts.add(post);

        // Stubba findAll-metoden på postRepository för att returnera en lista
        when(postRepository.findAll()).thenReturn(posts);

        // Anropa getAllPosts-metoden på postService
        List<Post> returnedPosts = postService.getAllPosts();

        // Verifiera att den returnerar samma lista som skapades
        assertEquals(posts, returnedPosts);

        // Verifiera att findAll-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void getPostById() {
        // Stubba findById-metoden på postRepository för att returnera ett Post-objekt
        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));

        // Anropa getPostById-metoden på postService
        Post returnedPost = postService.getPostById(1L);

        // Verifiera att den returnerar samma Post-objekt som skapades
        assertEquals(post, returnedPost);

        // Verifiera att findById-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void createPost() {
        // Stubba save-metoden på contentRepository för att returnera ett Content-objekt
        when(contentRepository.save(content)).thenReturn(content);

        // Stubba save-metoden på postRepository för att returnera ett Post-objekt
        when(postRepository.save(post)).thenReturn(post);

        // Anropa createPost-metoden på postService
        Post returnedPost = postService.createPost(post);

        // Verifiera att den returnerar samma Post-objekt som skapades
        assertEquals(post, returnedPost);

        // Verifiera att save-metoden på contentRepository anropas exakt en gång
        verify(contentRepository, times(1)).save(content);

        // Verifiera att save-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void updatePost() {
        // Ändra något i Post-objektet
        post.setTitle("Updated Title");

        // Stubba save-metoden på contentRepository för att returnera ett Content-objekt
        when(contentRepository.save(content)).thenReturn(content);

        // Stubba save-metoden på postRepository för att returnera ett Post-objekt
        when(postRepository.save(post)).thenReturn(post);

        // Anropa updatePost-metoden på postService
        Post returnedPost = postService.updatePost(post);

        // Verifiera att den returnerar samma Post-objekt som skapades
        assertEquals("Updated Title", returnedPost.getTitle());

        // Verifiera att save-metoden på contentRepository anropas exakt en gång
        verify(contentRepository, times(1)).save(content);

        // Verifiera att save-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void deletePost() {
        // Anropa deletePost-metoden på postService
        postService.deletePost(1L);

        // Verifiera att deleteById-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).deleteById(1L);
    }

}