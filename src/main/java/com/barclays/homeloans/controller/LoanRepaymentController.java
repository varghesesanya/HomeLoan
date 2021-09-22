package com.barclays.homeloans.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.model.LoanRepayment;
import com.barclays.homeloans.service.LoanRepaymentService;
import com.barclays.homeloans.service.LoanService;

@RestController
@EnableScheduling
public class LoanRepaymentController {
	
	@Autowired
	LoanRepaymentService loanRepaymentService;
	
	@Autowired
	LoanService loanService;
	
	
	@Scheduled(cron = "0 * * ? * *")
	public void checkLoanRepayment()
	{
		System.out.println("Running Scheduler");
		List<Loan> loans = loanService.getLoans();
		LoanRepayment loanRepayment;
		for(Loan loan : loans)
		{
			System.out.println(loan);
			loanRepayment = loan.getLoanRepayment().get(loan.getLoanRepayment().size()-1);
			
			
			double emi = loanRepayment.getEmi();
			double accountBalance = loan.getSavingsAccount().getBalance();
			loan.getSavingsAccount().setBalance(accountBalance-emi);
			loanRepayment.setStatus("Paid");
			
			System.out.println(loan);
        	
			loanService.insertLoan(loan);
			
			double interest = loanService.calculateInterest(loanRepayment.getOutstanding(), loan.getInterestRate());
        	Date date = new Date();
        	date.setMonth(date.getMonth()+1);
        	double principalAmount = emi-interest;
        	double outstanding = loanRepayment.getOutstanding() - principalAmount;
        	emi = Math.max(emi,outstanding);       	
        	if(emi==0)
        		loan.setStatus("Closed");
        	else
        	{
        		LoanRepayment lr = new LoanRepayment(emi,interest,principalAmount,"Pending",outstanding,date,loan);
            	loanRepaymentService.createLoanRepayment(lr);
        	}
			
			
			
		}
	}

}
