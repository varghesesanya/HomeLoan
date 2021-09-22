package com.barclays.homeloans.service;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.homeloans.model.User;
import com.barclays.homeloans.repository.UserRepository;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;




@Repository
public class UserService {
	@Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    
//    Used to verify a username and password
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


//    Used to fetch all users
    public User getUsers( String id) {
        Optional<User> u= userRepository.findById(Long.parseLong(id));
        if(u.isPresent()) return u.get();
        return null;
    }
    
//    Used to send email as an emi alert and loan approval based on the flag isEmiOn
    public String mail(String id,String isEmiOn){
    	Optional<User> u= userRepository.findById(Long.parseLong(id));
        if(u.isPresent()) {
	        String to = u.get().getEmail();   
	   		SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(to);
	        if("true".equals(isEmiOn)) {
	        	msg.setSubject("EMI deducted successfully");
	            msg.setText("Hello user, your emi is successfully deducted from your bank account for the  following month.");
	        }
	        else {
	        	msg.setSubject("Congratulations User loan Approved.");
	            msg.setText("Hello User Your loan application has been approved. Kindly check the status of your emi.");
	        }
	        javaMailSender.send(msg);
	        System.out.println("message sent successfully....");  
	        return "Mail sent successfully on the Email address: "+to;

   	   }
        return "Sorry! We couldn't find an email address with the given Account number.";

    }
    
    
}