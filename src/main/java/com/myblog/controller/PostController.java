package com.myblog.controller;

import com.myblog.entity.Post;
import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/all")
    public List<PostDto> getAllPost(
            @RequestParam(name = "pageNo", required = false,defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "id") String sortDir
    ){
       List<PostDto> postDtos = postService.getAllPost(pageNo, pageSize,sortBy,sortDir);
       return postDtos;
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable long id, @RequestBody PostDto postDto){
        PostDto dto = postService.updatePostById(id, postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        String s = postService.deleteById(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
