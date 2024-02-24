package com.myblog.service;

import com.myblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto updatePostById(long id, PostDto postDto);

    String deleteById(long id);
}
