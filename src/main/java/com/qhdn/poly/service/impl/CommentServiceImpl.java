package com.qhdn.poly.service.impl;

import com.qhdn.poly.dto.CommentDto;
import com.qhdn.poly.exceptions.ResourceNotFoundException;
import com.qhdn.poly.model.Comment;
import com.qhdn.poly.model.Post;
import com.qhdn.poly.repository.CommentRepo;
import com.qhdn.poly.repository.PostRepo;
import com.qhdn.poly.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment saveComment = this.commentRepo.save(comment);

        return this.modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment ID", commentId));

        this.commentRepo.delete(comment);
    }
}
