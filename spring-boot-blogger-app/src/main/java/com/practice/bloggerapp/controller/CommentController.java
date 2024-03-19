package com.practice.bloggerapp.controller;

import com.practice.bloggerapp.payload.CommentDTO;
import com.practice.bloggerapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("post/{postid}/comments")
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable("postid") Long postId,
            @RequestBody CommentDTO commentDTO
    ) {
        CommentDTO savedCommentDTO = commentService.saveComment(commentDTO, postId);
        return new ResponseEntity<>(savedCommentDTO, HttpStatus.CREATED);
    }

    @GetMapping("post/{postid}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(
            @PathVariable("postid") Long postId
    ) {
        List<CommentDTO> commentsByPostId = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentsByPostId, HttpStatus.OK);

    }

    @GetMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable long postId,
            @PathVariable long commentId
    ) {
        CommentDTO commentById = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentById, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable long postId,
            @PathVariable long commentId,
            @RequestBody CommentDTO commentDTO
    ) {
        CommentDTO updatedComment = commentService.updateComment(postId, commentId, commentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable long postId,
            @PathVariable long commentId
    ) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment Deleted Successfully");
    }
}
