package com.practice.bloggerapp.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    @NotEmpty
    @Size(min = 2,message = "Post Title should have 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post description should have 10 characters")
    private String description;
    @NotEmpty
    @Size(min = 5,message = "Post content should have 5 characters")
    private String content;
}
