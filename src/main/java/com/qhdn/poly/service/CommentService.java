package com.qhdn.poly.service;

import com.qhdn.poly.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
