package com.barclays.homeloans.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.model.LoanRepayment;
import com.barclays.homeloans.model.SavingsAccount;
import com.barclays.homeloans.service.LoanRepaymentService;
import com.barclays.homeloans.service.LoanService;
import com.barclays.homeloans.service.SavingsAccountService;

@RestController
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@Autowired 
	private LoanRepaymentService loanRepaymentService;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	@PostMapping("loan/apply/{id}/{salary}")
    public String loanApplication(@RequestBody Loan loan, @PathVariable long id, @PathVariable long salary) {
//		System.out.println(lo);
		
//		System.out.println(id);
//		System.out.println(salary);
		
//		Loan loan = new Loan();
//		Long l = new Long(5000000);
//		Long li = new Long(120);
//		loan.setTotalLoanAmount(l);
//		loan.setInterestRate(12);
//		loan.setTenure(li);
//		loan.setStatus("Ongoing");        
        
        SavingsAccount savingsAccount = savingsAccountService.getAccountById(id);
        loan.setSavingsAccount(savingsAccount);
        
        
        if(loanService.loanApplication(loan,salary))
        {
//        	loan = loanService.insertLoan(loan);

        	double emi = loanService.calculateEMI(loan.getLoanId());
        	double interest = loanService.calculateInterest(loan.getTotalLoanAmount(), loan.getInterestRate());
        	Date date = new Date();
        	date.setMonth(date.getMonth()+1);
        	double principalAmount = emi-interest;
        	double outstanding = loan.getTotalLoanAmount() - principalAmount;
        	
        	LoanRepayment lr = new LoanRepayment(emi,interest,principalAmount,"Pending",outstanding,date);
        	
        	System.out.println(loan);
        	List<LoanRepayment> lr_new = new ArrayList<LoanRepayment>(); 
//        	loan.setLoanRepayment(lr);
        	
        	lr_new.add(lr);
        	loan.setLoanRepayment(lr_new);
        	
//        	loan.getLoanRepayment().add(lr);
        	loan = loanService.insertLoan(loan);
        	

        	return "Loan Application Successfull";
        }
        else return "Loan Application Not Successfull";
        
    }
	
	
	@GetMapping("showemi/{id}")
    public double showemi(@PathVariable Long id) {
        return loanService.calculateEMI(id);
    }

}
