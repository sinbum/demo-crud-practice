package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestService testService;

//    @GetMapping
//    public String hellWorld(){
//        return "hello world";
//    }

    @GetMapping("/test")
    public List<Member> getAllMember(){
        List<Member> members = testService.getAllmembers();
        return members;
    }


}
