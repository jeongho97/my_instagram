package com.posco.insta.follow.service;

import com.posco.insta.follow.model.FollowDto;
import com.posco.insta.follow.repository.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{
    @Autowired //쿼리문 실행위해
    FollowMapper followMapper;

    @Override
    public List<FollowDto> getFollowing(FollowDto followDto) {
        return followMapper.getFollowing(followDto);
    }

    @Override
    public List<FollowDto> getFollower(FollowDto followDto) {
        return followMapper.getFollower(followDto);
    }

    @Override
    public FollowDto getFollowerByOne(FollowDto followDto) {
        return followMapper.getFollowerByOne(followDto);
    }

    @Override
    public Integer insertFollow(FollowDto followDto) {
        return followMapper.insertFollowing(followDto);
    }

    @Override
    public Integer deleteFollow(FollowDto followDto) {
        return followMapper.deleteFollow(followDto);
    }
}
