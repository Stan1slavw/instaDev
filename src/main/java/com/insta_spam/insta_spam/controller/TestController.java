package com.insta_spam.insta_spam.controller;

import com.insta_spam.insta_spam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {

    @Autowired
    public UserService userService;

    @GetMapping("/main")
    public String testMain(){
        return "main-page";
    }
}
