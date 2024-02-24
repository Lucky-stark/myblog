package com.myblog.controller;

import com.myblog.entity.Comment;
import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody Comment comment, @PathVariable long postId){
        CommentDto commentDto = commentService.saveComment(comment, postId);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        String deletedMessage = commentService.deleteComment(id);
        return new ResponseEntity<>(deletedMessage, HttpStatus.OK);
    }
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id, @RequestBody Comment comment, @PathVariable long postId){
        CommentDto dto = commentService.updateComment(id, comment, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
