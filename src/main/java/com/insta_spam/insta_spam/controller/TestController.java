package com.insta_spam.insta_spam.controller;

import com.github.instagram4j.instagram4j.IGClient;
import com.insta_spam.insta_spam.entity.InstagramUser;
import com.insta_spam.insta_spam.service.TestUserService;
import com.insta_spam.insta_spam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    public TestUserService testUserService;

    @GetMapping("/test")
    public String testMain(@ModelAttribute("username") String username, @ModelAttribute("password") String password, Model model){
        if (username.isEmpty()){
            username = null;
        }
        if (password.isEmpty()){
            password = null;
        }
        model.addAttribute("username", username);
        return "main-page";
    }

    @GetMapping("/testloginInst")
    public String loginToInst(){
        return "loginToInst";
    }

    @PostMapping("/testloginInst")
    public String logging(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirectAttributes){
        IGClient client = testUserService.getUser(username, password);
        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("password", password);
        return "redirect:/";
    }

    @GetMapping("/testgetInfo")
    public String getInfo(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        InstagramUser instagramUser = new InstagramUser();
        List<String> userInfoList = testUserService.getInfoAboutUser(instagramUser);
        model.addAttribute("userInfoList", userInfoList);
        return "UserInfo";
    }
}
