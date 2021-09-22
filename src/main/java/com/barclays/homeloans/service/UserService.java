package com.barclays.homeloans.service;

import java.util.Optional;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
        if (user.getPassword().trim().length() == 0) return "Bad password! It cannot be empty";
        String hash = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(hash);
        userRepository.save(user);
        savingsAccountService.createNewAccount(user, 5000L);
        return " Hello " + user.getName();
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