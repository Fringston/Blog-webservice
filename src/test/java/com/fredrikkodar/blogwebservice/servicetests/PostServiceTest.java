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
public class PostServiceTest {

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
        content.setText("Test Content");

        user = new User();
        user.setUsername("Test User");
        user.setPassword("Test Password");

        post = new Post();
        post.setTitle("Test Title");
        post.setContent(content);
        post.setUser(user);
        post.setDate("2021-09-01");
        post.setCategory("Test Category");
    }

    @Test
    void getAllPostsTest() {
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
    void getPostByIdTest() {
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
    void createPostTest() {
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
    void updatePostTest() {
        // Skapa ett nytt Post-objekt som representerar det uppdaterade inlägget
        Post updatedPost = new Post();
        updatedPost.setTitle("Updated Title");
        updatedPost.setContent(content);
        updatedPost.setUser(user);
        updatedPost.setCategory("Updated Category");

        // Stubba findById-metoden på postRepository för att returnera det ursprungliga Post-objektet
        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));

        // Stubba save-metoden på postRepository för att returnera ett Post-objekt när det kallas med vilket Post-objekt som helst
        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Anropa updatePost-metoden på postService
        Post returnedPost = postService.updatePost(1L, updatedPost);

        // Verifiera att det returnerade Post-objektet har de uppdaterade fälten
        assertEquals(updatedPost.getTitle(), returnedPost.getTitle());
        assertEquals(updatedPost.getContent(), returnedPost.getContent());
        assertEquals(updatedPost.getUser(), returnedPost.getUser());
        assertEquals(updatedPost.getCategory(), returnedPost.getCategory());

        // Verifiera att det returnerade Post-objektet har samma datum som det ursprungliga Post-objektet
        assertEquals(post.getDate(), returnedPost.getDate());

        // Verifiera att findById-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).findById(1L);

        // Verifiera att save-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void deletePostTest() {
        // Stubba existsById-metoden på postRepository för att returnera true
        when(postRepository.existsById(1L)).thenReturn(true);

        // Anropa deletePost-metoden på postService
        postService.deletePost(1L);

        // Verifiera att existsById-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).existsById(1L);

        // Verifiera att deleteById-metoden på postRepository anropas exakt en gång
        verify(postRepository, times(1)).deleteById(1L);
    }
}
