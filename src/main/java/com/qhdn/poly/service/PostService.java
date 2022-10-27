package com.qhdn.poly.service;

import com.qhdn.poly.dto.PostDto;
import com.qhdn.poly.dto.PostResponsive;
import com.qhdn.poly.model.Post;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all post
    PostResponsive getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by account
    List<PostDto> getPostsByUser(Integer userId);

    //search post
    List<PostDto> searchPosts(String keyword);
}
