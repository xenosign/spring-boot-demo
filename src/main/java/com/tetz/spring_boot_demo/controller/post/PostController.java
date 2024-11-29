package com.tetz.spring_boot_demo.controller.post;

import com.tetz.spring_boot_demo.dto.post.PostDto;
import com.tetz.spring_boot_demo.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostDto>> findAllPosts(Model model) {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) {
        PostDto savedPost = postService.createPost(postDto);
        return ResponseEntity.ok(savedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("삭제 성공");
    }
}