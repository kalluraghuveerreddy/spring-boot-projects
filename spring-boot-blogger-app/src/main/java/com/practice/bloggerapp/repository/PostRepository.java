package com.practice.bloggerapp.repository;

import com.practice.bloggerapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
