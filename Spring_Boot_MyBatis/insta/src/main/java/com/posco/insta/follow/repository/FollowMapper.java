package com.posco.insta.follow.repository;

import com.posco.insta.follow.model.FollowDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    List<FollowDto> getFollowing(FollowDto followDto);
    List<FollowDto> getFollower(FollowDto followDto);
    Integer insertFollowing(FollowDto followDto);
    Integer deleteFollow(FollowDto followDto);
    FollowDto getFollowerByOne(FollowDto followDto);
}
