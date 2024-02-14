package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postgre")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<PostDto> getPostById(@RequestParam long id){
        PostDto postById = postService.getPostById(id);
        return new ResponseEntity<>(postById, HttpStatus.OK);
    }
}
