package com.insta_spam.insta_spam.controller;

import com.github.instagram4j.instagram4j.IGClient;
import com.insta_spam.insta_spam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String testMain(@ModelAttribute("username") String username, Model model){
        model.addAttribute("username", username);
//        userService.login();
//        userService.uploadPhoto();
//        userService.getInfoAboutUser();
//        userService.setProfilePicture();
        return "main-page";
    }

    @GetMapping("/loginInst")
    public String loginToInst(){
        return "loginToInst";
    }

    @PostMapping("/loginInst")
    public String logging(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirectAttributes){
       IGClient client = userService.getUser(username, password);
        redirectAttributes.addAttribute("username", username);
        return "redirect:/";
    }

    @GetMapping("/getInfo")
    public String getInfo(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        List<String> userInfoList = userService.getInfoAboutUser(username, password);
        model.addAttribute("userInfoList", userInfoList);
        return "UserInfo";
    }
}
