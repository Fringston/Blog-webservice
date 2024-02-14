package com.fredrikkodar.blogwebservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="content")
public class Content {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String text;

}
