package com.tetz.spring_boot_demo.repository.post;

import com.tetz.spring_boot_demo.dto.post.PostDto;
import com.tetz.spring_boot_demo.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
}
