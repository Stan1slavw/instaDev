package com.insta_spam.insta_spam.controller;

import com.github.instagram4j.instagram4j.IGClient;
import com.insta_spam.insta_spam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String testMain(@RequestParam("username") String username, Model model){
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
       client.actions().account().setBio("testBio2").join();
        redirectAttributes.addAttribute("username", username);
        return "redirect:/";
    }
}
