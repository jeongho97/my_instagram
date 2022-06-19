package com.posco.insta.post.repository;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostMapper {
    List<PostDto>getPosts();
    List<SelectPostJoinUserDto>findPostsByUserId(PostDto postDto);

    Integer deletePostByUserIdAndId(PostDto postDto);

    List<SelectPostJoinUserDto> findPostsByNotUserId(PostDto postDto);
    Integer updatePostByUserAndId(PostDto postDto);

    Integer insertPost(PostDto postDto);
    SelectPostJoinUserDto getPostsById(PostDto postDto);

    List<SelectPostJoinUserDto> getPostsByKey(String Key);

    List<SelectPostJoinUserDto> getPostsByMyFollowing(PostDto postDto);
}
