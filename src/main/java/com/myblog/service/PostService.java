package com.myblog.service;

import com.myblog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);
}
