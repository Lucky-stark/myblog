package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto saveComment(Comment comment, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Cannot save comment , Post Id is not available" + postId));
        comment.setPost(post);
        Comment save = commentRepository.save(comment);

        CommentDto commentDto = mapToDto(save);

        return commentDto;
    }

    @Override
    public String deleteComment(long id) {
        Comment cmtWithId = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id is not present in database_" + id));
        commentRepository.delete(cmtWithId);
        return "Comment deleted sucessfully with id_"+id;
    }

    @Override
    public CommentDto updateComment(long id, Comment comment, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post id is not present in db" + postId)
        );
        Comment commentId = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment id is not present in db" + id)
        );
        commentId.setText(comment.getText());
        commentId.setEmail(comment.getEmail());
        commentId.setPost(post);
        Comment save = commentRepository.save(commentId);
        return mapToDto(save);
    }

    public CommentDto mapToDto(Comment comment){
//        CommentDto dto = new CommentDto();
//        dto.setId(comment.getId());
//        dto.setEmail(comment.getEmail());
//        dto.setText(comment.getText());
//        return dto;
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }
    public Comment mapToEntity(CommentDto dto){
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }
}
