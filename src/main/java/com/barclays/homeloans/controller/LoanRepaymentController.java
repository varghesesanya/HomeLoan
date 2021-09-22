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
        	
			loanService.insertLoan(loan);
			
			if(loanRepayment.getOutstanding() != 0)
			{
				double interest = loanService.calculateInterest(loanRepayment.getOutstanding(), loan.getInterestRate());
	        	Date date = loanRepayment.getDate();
	        	date.setMonth(date.getMonth()+1);
	        	
	        	double principalAmount;
	        	if(emi > loanRepayment.getOutstanding())
	        	{
	        		principalAmount = loanRepayment.getOutstanding();
	        		emi = principalAmount;
	        	}
	        	else
	        		principalAmount = emi-interest;
	        	
	        	System.out.println(loan);
	        	double outstanding = loanRepayment.getOutstanding() - principalAmount;
	        	
	        	LoanRepayment lr = new LoanRepayment(emi,interest,principalAmount,"Pending",outstanding,date);
        		loan.getLoanRepayment().add(lr);
			}
			else
				loan.setStatus("Closed");
        	       	
//        	if(emi==0)
//        		
//        	else
//        	{
//        		
//        		//System.out.println(loan);
//        	}
        	
        	loanService.insertLoan(loan);
			
			
			
		}
	}

}
