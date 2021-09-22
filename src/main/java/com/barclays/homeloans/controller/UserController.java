package com.barclays.homeloans.controller;

import java.util.Map;

import javax.persistence.MapKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloans.model.User;
import com.barclays.homeloans.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.registerUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) {
        return userService.authentication(user);

    }

    @GetMapping("user/{id}")
    public User login(@PathVariable String id) {
        return userService.getUsers(id);
    }
    
    
    @PostMapping("sendMail")
    public String mail(@RequestBody Map<String,String> json) {
    	String id=json.get("customerId");
        String isEmiOn= json.get("emiStatus");
        
        return userService.mail(id,isEmiOn);
    }


}