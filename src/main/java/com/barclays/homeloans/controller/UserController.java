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


    @PostMapping("login")
    public String login(@RequestBody Map<String, String> json) {
        String customerId=json.get("customerId");
        String password= json.get("password");
        return userService.authentication(customerId,password);
    }

    @GetMapping("showuser/{id}")
    public User login(@PathVariable String id) {
        return userService.getUsers(id);
    }


}