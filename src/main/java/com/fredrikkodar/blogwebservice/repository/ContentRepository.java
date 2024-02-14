package com.fredrikkodar.blogwebservice.repository;

import com.fredrikkodar.blogwebservice.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

}
