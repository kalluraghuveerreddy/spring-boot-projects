package com.practice.bloggerapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    private String title;
    private String description;
    private String content;
}
