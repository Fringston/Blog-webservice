package com.fredrikkodar.blogwebservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id", referencedColumnName = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String date;

    private String category;

}