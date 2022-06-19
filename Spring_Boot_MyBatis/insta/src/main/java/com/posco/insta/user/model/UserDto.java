package com.posco.insta.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data //getter setter toString 다 갖고있음
@Component //다른 곳에서 Autowired로 갖다 쓸 수 있다
public class UserDto {
    private Integer id;
    private String password;
    private String userId;
    private String img;
    private String name;
}
