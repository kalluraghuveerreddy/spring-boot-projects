package com.practice.bloggerapp.service;

import com.practice.bloggerapp.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO saveComment(CommentDTO commentDTO, Long postId);
    List<CommentDTO> getCommentsByPostId(long postId);

    CommentDTO getCommentById(long postId,long commentId);
    CommentDTO updateComment(Long postId,Long commentId, CommentDTO commentDTO);
    void deleteComment(Long postId, Long commentId);
}
