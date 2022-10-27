package com.qhdn.poly.controller.rest;

import com.qhdn.poly.config.AppConstants;
import com.qhdn.poly.dto.ApiResponsive;
import com.qhdn.poly.dto.PostDto;
import com.qhdn.poly.dto.PostResponsive;
import com.qhdn.poly.model.Post;
import com.qhdn.poly.service.CategoryService;
import com.qhdn.poly.service.FileService;
import com.qhdn.poly.service.PostService;
import com.qhdn.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/add/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatePost = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ApiResponsive deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);

        return new ApiResponsive("Post is successfully deleted!!" + postId, true);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId){
        List<PostDto> postDtoList = this.postService.getPostsByUser(userId);

        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtoList = this.postService.getPostsByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

    //pageNumber la so trang, pageSize la kich thuoc moi trang
    @GetMapping("")
    public ResponseEntity<PostResponsive> getAllPost(
            @RequestParam(value = "pageNumber",
                    defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",
                    defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",
                    defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir",
                    defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ){
        PostResponsive postResponsive = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<PostResponsive>(postResponsive, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchByKeyWord(@PathVariable String keywords){
        List<PostDto> postDtoList = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

    //post image upload
    @PostMapping("/image/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws Exception {
        String fileName = this.fileService.uploadImage(path, image);
        PostDto postDto = this.postService.getPostById(postId);
        postDto.setImageName(fileName);
        this.postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }
}
