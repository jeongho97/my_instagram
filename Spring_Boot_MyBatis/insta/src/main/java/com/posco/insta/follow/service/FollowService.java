package com.posco.insta.follow.service;

import com.posco.insta.follow.model.FollowDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowService {
    List<FollowDto> getFollowing(FollowDto followDto);
    List<FollowDto> getFollower(FollowDto followDto);
    FollowDto getFollowerByOne(FollowDto followDto);
    Integer insertFollow(FollowDto followDto);
    Integer deleteFollow(FollowDto followDto);
}
