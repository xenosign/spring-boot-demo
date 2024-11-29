package com.tetz.spring_boot_demo.service.post;

import com.tetz.spring_boot_demo.dto.post.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDto> findAll();
    PostDto createPost(PostDto postDto);
    void deletePost(Long id);
}
