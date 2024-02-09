package com.myblog.service.impl;

import com.myblog.entity.Post;
import com.myblog.payload.PostDto;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post save = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setId(save.getId());
        dto.setTitle(save.getTitle());
        dto.setDescription(save.getDescription());
        dto.setContent(save.getContent());
        return dto;
    }
}
