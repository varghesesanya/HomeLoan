package com.barclays.homeloans.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.homeloans.model.User;
import com.barclays.homeloans.repository.UserRepository;



@Repository
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String authentication(String customerId, String password) {
        try {
            Optional<User> u= userRepository.findById(Long.parseLong(customerId));
            if(u.isPresent()) {
                if(u.get().getPassword().equals(password)) return "Welcome "+ u.get().getName();
            }
            throw new Exception("Invalid Credentials. Please retry.");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "Invalid Credentials. Please retry";
        }

    }


    public User getUsers( String id) {
        Optional<User> u= userRepository.findById(Long.parseLong(id));
        if(u.isPresent()) return u.get();
        return null;
    }
}