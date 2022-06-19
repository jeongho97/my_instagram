package com.posco.insta.post.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component //Bean에 올리는 방법
public class PostDto {
    private Integer id;
    private Integer userId;
    private String img;
    private String content;
}
