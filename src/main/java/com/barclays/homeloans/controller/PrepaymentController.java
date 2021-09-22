package com.barclays.homeloans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloans.model.User;
import com.barclays.homeloans.service.PrepaymentService;
import com.barclays.homeloans.service.UserService;

@RestController
public class PrepaymentController {
	 @Autowired
	 private PrepaymentService prepaymentService;
	 

	    @GetMapping("prepayment/{id}/{amount}")
	    public String getprepayment(@PathVariable Long id,@PathVariable Long amount) {
	        return prepaymentService.prepayment(id, amount);
	        
	    }
	    
	    @GetMapping("foreclosure/{id}")
	    public String foreclosure(@PathVariable Long id ) {
	        return prepaymentService.foreclosure(id);
	        
	    }
}
