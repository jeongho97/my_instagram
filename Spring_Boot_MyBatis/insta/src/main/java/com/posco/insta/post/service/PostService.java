package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService {
    List<PostDto> getPosts();
    List<SelectPostJoinUserDto>getPostByUserId(PostDto postDto);

    Integer deleteMyPost(PostDto postDto);

    List<SelectPostJoinUserDto> getPostsByNotUserId(PostDto postDto);

    Integer updateMyPost(PostDto postDto);

    Integer postPost(PostDto postDto);

    SelectPostJoinUserDto getPostById(PostDto postDto);

    List<SelectPostJoinUserDto> findPostsLikeKey(String Key);

    List<SelectPostJoinUserDto> getPostsByMyFollowing(PostDto postDto);
}
