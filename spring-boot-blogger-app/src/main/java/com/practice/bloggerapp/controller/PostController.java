package com.practice.bloggerapp.controller;

import com.practice.bloggerapp.payload.PostDTO;
import com.practice.bloggerapp.payload.PostResponse;
import com.practice.bloggerapp.service.PostService;
import com.practice.bloggerapp.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*
 * @Author kallu Raghuveer Reddy
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<PostDTO> post(@Valid @RequestBody PostDTO postDTO) {
        PostDTO savedPostDTO = postService.save(postDTO);
        return new ResponseEntity<>(savedPostDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public PostResponse getPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);


    }

    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        PostDTO postDTO = postService.getPostById(id);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDTO> updatedPost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostDTO updatedPostDto = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post Deleted Successfully");
    }


}
