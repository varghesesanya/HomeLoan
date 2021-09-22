package com.barclays.homeloans.service;

import java.util.Optional;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.homeloans.model.User;
import com.barclays.homeloans.repository.UserRepository;



@Repository
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SavingsAccountService savingsAccountService;

    public String authentication(User user) {
        try {
            Optional<User> u= userRepository.findById(user.getCustomerid());
            if(u.isPresent()) {
                BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), u.get().getPassword().toCharArray());
                if(result.verified) return "Welcome "+ u.get().getName();
            }
            throw new Exception("Invalid Credentials. Please retry.");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "Invalid Credentials. Please retry";
        }

    }


    public User getUsers(String id) {
        Optional<User> u= userRepository.findById(Long.parseLong(id));
        if(u.isPresent()) return u.get();
        return null;
    }


    public String registerUser(User user) {
        String hash = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(hash);
        userRepository.save(user);
        savingsAccountService.createNewAccount(user, 5000L);
        return " Hello " + user.getName();
    }

}