package com.posco.insta.user.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j

public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserDto userDto;

    @GetMapping("/")
    public List<UserDto> getUser(){
        return userService.findUser();
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id){

        userDto.setId(Integer.valueOf(id));
        return userService.findUserById(userDto);
    }
//    @PostMapping("/")
//    public Integer postUser(@RequestBody UserDto userDto){
//        return userService.insertUser(userDto);
//    }

    @PostMapping("/")
    public ResponseEntity<?> postUser(@RequestBody UserDto userDto)
    {
        HttpStatus httpStatus = userService.insertUser(userDto)==1
                ?HttpStatus.CREATED: HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping("/")
    @TokenRequired
    public Integer deleteUserById(){

        return userService.deleteUserById(securityService.getIdAtToken());
    }

    @PutMapping("/{id}")
    @Operation(description = "정보업데이트")
    public Integer updateUserById(@RequestBody UserDto userDto
            ,@PathVariable String id){
        userDto.setId(Integer.valueOf(id));
        return userService.updateUserById(userDto);
    }

    @PostMapping("/login")
    @Operation(description = "로그인")
    public Map Login(@RequestBody UserDto userDto){
        UserDto loginUser=userService.serviceLogin(userDto);
        String token=securityService.createToken(loginUser.getId().toString());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("name",loginUser.getName());
        map.put("img",loginUser.getImg());
        return map;
    }
    @GetMapping("/token")
    @TokenRequired
    public String getToken(){
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer=request.getHeader("Authorization");//token을 parameter로 보내는게 아니라 header에 Authorization로 담아 보낸다

        String subject=securityService.getSubject(tokenBearer);
        return subject;
    }

    @GetMapping("/me")
    @TokenRequired //로그인 하고 나서 token이 생성되는데 token을 사용하기 위해 실제로는 로그인 회원가입과 같이 id값이 필요없는 부분 빼고는 다 token이 있어야한다
    public UserDto getUserByMe(){
        log.info("controller");
        int id = securityService.getIdAtToken();
        //실행 로직

        userDto.setId(id);
        return userService.findUserById(userDto);
    }

    @TokenRequired
    @GetMapping("/check")
    public Boolean check(){
        return true;
    }


}
