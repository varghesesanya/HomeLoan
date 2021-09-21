package com.barclays.homeloans.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.service.LoanService;

@RestController
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping("loan/apply")
    public String login(@RequestBody Map<String, Object> json) {
		Loan loan = (Loan) json.get("loanDetails");
        String monthlySalary= json.get("monthlySalary").toString();
        return loanService.loanApplication(loan,Integer.parseInt(monthlySalary));
    }

}
