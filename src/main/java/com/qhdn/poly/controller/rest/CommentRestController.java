package com.qhdn.poly.controller.rest;

import com.qhdn.poly.dto.ApiResponsive;
import com.qhdn.poly.dto.CommentDto;
import com.qhdn.poly.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    @Autowired
    CommentService commentService;

    @PostMapping("/{postId}/create")
    public ResponseEntity<CommentDto> saveComment(
            @RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto commentDto1 = this.commentService.createComment(commentDto, postId);

        return new ResponseEntity<CommentDto>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{commentId}")
    public ResponseEntity<ApiResponsive> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);

        return new ResponseEntity<ApiResponsive>(
                new ApiResponsive("Comment is deleted!! " + commentId, true), HttpStatus.OK);
    }

}
