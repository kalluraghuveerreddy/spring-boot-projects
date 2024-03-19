package com.practice.bloggerapp.service;

import com.practice.bloggerapp.entity.Comment;
import com.practice.bloggerapp.entity.Post;
import com.practice.bloggerapp.exception.BlogApiException;
import com.practice.bloggerapp.exception.ResourceNotFoundException;
import com.practice.bloggerapp.payload.CommentDTO;
import com.practice.bloggerapp.repository.CommentRepository;
import com.practice.bloggerapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            PostRepository postRepository,
            ModelMapper modelMapper
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        List<Comment> commentsList = commentRepository.findByPostPostId(postId);
        List<CommentDTO> commentDTOList = commentsList.stream()
                .map(c -> modelMapper.map(c, CommentDTO.class))
                .collect(Collectors.toList());

        return commentDTOList;
    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        if (!comment.getPost().getPostId().equals(post.getPostId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comments does not belong to Post");
        }

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

        if (!comment.getPost().getPostId().equals(post.getPostId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comments does not belong to Post");
        }

        comment.setBody(commentDTO.getBody());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        Comment updatedComment = commentRepository.save(comment);
        return modelMapper.map(updatedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        if (!comment.getPost().getPostId().equals(post.getPostId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comments does not belong to Post");
        }
        commentRepository.delete(comment);
    }
}
