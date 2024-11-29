package com.tetz.spring_boot_demo.service.post;

import com.tetz.spring_boot_demo.dto.post.PostDto;
import com.tetz.spring_boot_demo.entity.post.Post;
import com.tetz.spring_boot_demo.repository.post.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> findAll() {
        return postRepository.findAll()
                .stream()
                .map(post -> new PostDto(
                        post.getId(),  // Long을 Integer로 변환
                        post.getBody(),
                        post.getAuthor()
                ))
                .collect(Collectors.toList());

    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post(postDto.getBody(), postDto.getAuthor());
        Post savedPost = postRepository.save(post);

        return new PostDto(
                savedPost.getId(),
                savedPost.getBody(),
                savedPost.getAuthor()
        );
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
