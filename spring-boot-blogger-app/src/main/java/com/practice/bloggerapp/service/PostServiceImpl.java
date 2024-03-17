package com.practice.bloggerapp.service;

import com.practice.bloggerapp.entity.Post;
import com.practice.bloggerapp.exception.ResourceNotFoundException;
import com.practice.bloggerapp.payload.PostDTO;
import com.practice.bloggerapp.payload.PostResponse;
import com.practice.bloggerapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDTO save(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postDTO.toString()));
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));
        postRepository.deleteById(post.getPostId());
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        //create the Pageable instance
        //Pageable pageable= PageRequest.of(pageNo,pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> page = postRepository.findAll(pageable);
        // get content from page object
        List<Post> posts = page.getContent();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(posts.stream().map(p -> modelMapper.map(p, PostDTO.class)).collect(Collectors.toList()));
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLast(page.isLast());

        return postResponse;
    }
}
