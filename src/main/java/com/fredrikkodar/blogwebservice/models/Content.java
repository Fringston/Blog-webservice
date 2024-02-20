package com.fredrikkodar.blogwebservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="content")
public class Content {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
