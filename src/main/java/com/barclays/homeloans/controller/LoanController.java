package com.barclays.homeloans.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.model.SavingsAccount;
import com.barclays.homeloans.service.LoanService;

@RestController
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping("loan/apply")
    public String login(@RequestBody Map<String,String> json) {
		Loan loan = new Loan();
		loan.setTotalLoanAmount(Long.parseLong(json.get("totalLoanAmount")));
		loan.setInterestRate(Double.parseDouble(json.get("interestRate")));
		loan.setTenure(Long.parseLong(json.get("tenure")));
		loan.setStatus(json.get("status"));
        String monthlySalary= json.get("monthlySalary").toString();
        return loanService.loanApplication(loan,Integer.parseInt(monthlySalary));
    }

}
