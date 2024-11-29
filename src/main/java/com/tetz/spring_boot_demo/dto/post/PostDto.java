package com.tetz.spring_boot_demo.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String body;
    private String author;

    public PostDto(String body, String author) {
        this.body = body;
        this.author = author;
    }
}
