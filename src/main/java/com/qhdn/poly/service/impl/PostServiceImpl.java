package com.qhdn.poly.service.impl;

import com.qhdn.poly.dto.PostDto;
import com.qhdn.poly.dto.PostResponsive;
import com.qhdn.poly.exceptions.ResourceNotFoundException;
import com.qhdn.poly.model.Category;
import com.qhdn.poly.model.Post;
import com.qhdn.poly.model.User;
import com.qhdn.poly.repository.CategoryRepo;
import com.qhdn.poly.repository.PostRepo;
import com.qhdn.poly.repository.UserRepo;
import com.qhdn.poly.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User", userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post addPost = this.postRepo.save(post);

        return this.modelMapper.map(addPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatePost = this.postRepo.save(post);

        return this.modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponsive getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = ((sortDir.equalsIgnoreCase("asc"))?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> postPage = this.postRepo.findAll(p);

        List<Post> posts = postPage.getContent();

        List<PostDto> postDtoList = posts.stream()
                .map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponsive postResponsive = new PostResponsive();
        postResponsive.setContent(postDtoList);
        postResponsive.setPageNumber(postPage.getNumber());
        postResponsive.setPageSize(postPage.getSize());
        postResponsive.setTotalElements(postPage.getTotalElements());

        postResponsive.setTotalPages(postPage.getTotalPages());
        postResponsive.setLastPage(postPage.isLast());

        return postResponsive;
    }

//    @Override
//    public List<PostDto> getAllPost() {
//
//        List<Post> posts = this.postRepo.findAll();
//
//        List<PostDto> postDtoList = posts.stream()
//                .map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//
//        return postDtoList;
//    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));

        List<Post> posts = this.postRepo.findByCategory(category);

        List<PostDto> postDtoList = posts.stream().map((post) -> modelMapper
                .map(post, PostDto.class)).collect(Collectors.toList());

        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User ID", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtoList = posts.stream().map((post)-> modelMapper
                .map(post, PostDto.class)).collect(Collectors.toList());

        return postDtoList;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtoList = posts.stream()
                .map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }
}
