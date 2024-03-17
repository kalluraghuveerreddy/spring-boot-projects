package com.practice.bloggerapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "title"
                )
        }
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator")
    @SequenceGenerator(
            name = "sequence_generator",
            sequenceName = "hibernate_sequence",
            allocationSize = 1
    )
    private Long postId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String content;
}
