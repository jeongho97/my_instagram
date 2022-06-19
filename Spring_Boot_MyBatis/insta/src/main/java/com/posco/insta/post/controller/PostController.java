package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.service.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@TokenRequired
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    SecurityService securityService;

    @Autowired
    PostDto postDto;

    @GetMapping("/")
    public List<PostDto> getPost(){
        return postService.getPosts();
    }
   /* @GetMapping("/my")
    public List<PostDto> getMyPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostByUserId(postDto);
    }*/

    @GetMapping("/my")
    @TokenRequired
    public List<SelectPostJoinUserDto> getMyPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostByUserId(postDto);
    }

    @GetMapping("/{id}")
    @TokenRequired
    public SelectPostJoinUserDto getPostsById(@PathVariable String id){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostById(postDto);
    }

    @DeleteMapping("/{id}")
    public Integer deleteMyPost(@PathVariable String id){
        postDto.setId(Integer.valueOf(id));
        postDto.setUserId(securityService.getIdAtToken());
        return postService.deleteMyPost(postDto);
    }

    @GetMapping("/other")
    public List<SelectPostJoinUserDto> getOtherPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostsByNotUserId(postDto);
    }

    @PutMapping("/{id}")
    public Integer updateMyPost(
            @RequestBody PostDto postDto,
            @PathVariable String id
    ){
        postDto.setUserId(securityService.getIdAtToken());
        postDto.setId(Integer.valueOf(id));
        return postService.updateMyPost(postDto);
    }

    @PostMapping("/")
    public Integer postPost(@RequestBody PostDto postDto){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.postPost(postDto);
    }

    @GetMapping("/key/{key}")
    public List<SelectPostJoinUserDto> getPostsLikeKey(@PathVariable String key){
        return postService.findPostsLikeKey(key);
    }

    @GetMapping("/following")
    @Operation(description = "내가 팔로윙으로 있고, follower인 애들의 getPost")
    public List<SelectPostJoinUserDto> getPostsByMyFollowing(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostsByMyFollowing(postDto);
    }

}
