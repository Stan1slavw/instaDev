package com.insta_spam.insta_spam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @GetMapping("/main")
    public String testMain(){
        return "main-page";
    }
}
