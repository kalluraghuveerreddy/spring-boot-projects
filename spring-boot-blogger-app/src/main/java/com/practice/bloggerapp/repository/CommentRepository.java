package com.practice.bloggerapp.repository;

import com.practice.bloggerapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostPostId(long postId);
}
