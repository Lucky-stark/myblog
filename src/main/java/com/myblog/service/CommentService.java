package com.myblog.service;

import com.myblog.entity.Comment;
import com.myblog.payload.CommentDto;

public interface CommentService {
    CommentDto saveComment(Comment comment, long postId);

    String  deleteComment(long id);

    CommentDto updateComment(long id, Comment comment, long postId);
}
