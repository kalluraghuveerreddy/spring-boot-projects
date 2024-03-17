package com.practice.bloggerapp.service;

import com.practice.bloggerapp.payload.PostDTO;
import com.practice.bloggerapp.payload.PostResponse;

public interface PostService {

    //To Save the post
    PostDTO save(PostDTO postDTO);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO postDTO, Long postId);

    void deletePostById(Long postId);

    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
}
