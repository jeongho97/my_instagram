package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.repository.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;
    @Override
    public List<PostDto> getPosts() {
        return postMapper.getPosts();
    }

    @Override
    public List<SelectPostJoinUserDto> getPostByUserId(PostDto postDto) {
        return postMapper.findPostsByUserId(postDto);
    }

    @Override
    public Integer deleteMyPost(PostDto postDto) {
        return postMapper.deletePostByUserIdAndId(postDto);
    }

    @Override
    public List<SelectPostJoinUserDto> getPostsByNotUserId(PostDto postDto) {
        return postMapper.findPostsByNotUserId(postDto);
    }

    @Override
    public Integer updateMyPost(PostDto postDto) {
        return postMapper.updatePostByUserAndId(postDto);
    }

    @Override
    public Integer postPost(PostDto postDto) {
        return postMapper.insertPost(postDto);
    }

    @Override
    public SelectPostJoinUserDto getPostById(PostDto postDto) {
        return postMapper.getPostsById(postDto);
    }

    @Override
    public List<SelectPostJoinUserDto> findPostsLikeKey(String Key) {
        return postMapper.getPostsByKey(Key);
    }

    @Override
    public List<SelectPostJoinUserDto> getPostsByMyFollowing(PostDto postDto) {
        log.info(postMapper.getPostsByMyFollowing(postDto).toString());
        return postMapper.getPostsByMyFollowing(postDto);
    }
}
