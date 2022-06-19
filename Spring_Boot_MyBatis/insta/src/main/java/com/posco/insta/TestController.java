package com.posco.insta;


import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    /*restful의 장점
    * uri 설계 하나만 하면 된다
    * user로 mapping 시킴으로써 user에 관한 crud를 한 곳에서 다 구현 가능
    * */
    @GetMapping("/")
    public String Test(){
        return "test";
    }

/*    @GetMapping("/{id}")
    public String testId(@PathVariable String id){
        return id;
    }

    @PostMapping("/")
    public String testPos(){
        return "hello world";
    }

    @DeleteMapping("/{id}")
    public String testDelete(@PathVariable String id){
        return "hello world"+id;
    }

    @PutMapping("/") *//*update*//*
    public String testPut(){
        return "hello world";
    }*/
}
