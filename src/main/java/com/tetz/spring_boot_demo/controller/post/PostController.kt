package com.tetz.spring_boot_demo.controller.post

import com.tetz.spring_boot_demo.dto.post.PostDto
import com.tetz.spring_boot_demo.service.post.PostService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
class PostController {
    private val postService: PostService? = null

    @GetMapping("")
    fun findAllPosts(model: Model?): ResponseEntity<List<PostDto>> {
        return ResponseEntity.ok(postService!!.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long?): ResponseEntity<PostDto> {
        return ResponseEntity.ok(postService!!.findById(id))
    }


    @PostMapping("")
    fun savePost(@RequestBody postDto: PostDto?): ResponseEntity<PostDto> {
        val savedPost = postService!!.createPost(postDto)
        return ResponseEntity.ok(savedPost)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long?): ResponseEntity<String> {
        postService!!.deletePost(id)
        return ResponseEntity.ok("삭제 성공")
    }
}