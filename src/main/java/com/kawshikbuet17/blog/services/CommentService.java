package com.kawshikbuet17.blog.services;

import com.kawshikbuet17.blog.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}
