package com.barclays.homeloans.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.model.LoanRepayment;
import com.barclays.homeloans.service.LoanRepaymentService;
import com.barclays.homeloans.service.LoanService;

@RestController
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@Autowired 
	private LoanRepaymentService loanRepaymentService;
	
	@PostMapping("loan/apply")
    public String login(@RequestBody Map<String,String> json) {
		Loan loan = new Loan();
		Long l = new Long(5000000);
		Long li = new Long(120);
		loan.setTotalLoanAmount(l);
		loan.setInterestRate(12);
		loan.setTenure(li);
		loan.setStatus("Ongoing");
        String monthlySalary= json.get(2500000).toString();
        if(loanService.loanApplication(loan,Integer.parseInt(monthlySalary)))
        {
        	loan = loanService.insertLoan(loan);
        	double emi = loanService.calculateEMI(loan.getLoanId());
        	double interest = loanService.calculateInterest(loan.getTotalLoanAmount(), loan.getInterestRate());
        	Date date = new Date();
        	date.setMonth(date.getMonth()+1);
        	double principalAmount = emi-interest;
        	double outstanding = loan.getTotalLoanAmount() - principalAmount;
        	
        	LoanRepayment lr = new LoanRepayment(emi,interest,principalAmount,"Pending",outstanding,date);
        	loanRepaymentService.createLoanRepayment(lr);
        	return "Loan Application Successfull";
        }
        else return "Loan Application Not Successfull";
        
    }
	
	
	@GetMapping("showemi/{id}")
    public double showemi(@PathVariable Long id) {
        return loanService.calculateEMI(id);
    }

}
