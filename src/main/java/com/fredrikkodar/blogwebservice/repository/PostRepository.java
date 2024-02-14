package com.fredrikkodar.blogwebservice.repository;

import com.fredrikkodar.blogwebservice.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
