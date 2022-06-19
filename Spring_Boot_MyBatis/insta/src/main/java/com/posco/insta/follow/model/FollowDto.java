package com.posco.insta.follow.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class FollowDto {
    private  Integer following;
    private  Integer follower;
}
