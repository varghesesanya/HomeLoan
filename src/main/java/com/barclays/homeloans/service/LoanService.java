package com.barclays.homeloans.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.model.User;
import com.barclays.homeloans.repository.LoanRepository;

@Repository
public class LoanService {
	
	@Autowired
	private LoanRepository loanRepository;
	
	public String loanApplication(Loan loan, Integer monthlySalary) {
		try {
			if(monthlySalary*50<= loan.getTotalLoanAmount()) {
				return "Sorry you're not eligible for a loan of such a large amount due to insufficient collateral, try again for a smaller amount.";
			}
			else if(5*12>loan.getTenure() || 20*12<loan.getTenure()){
				return "Sorry you're not eligible for a loan because of tenure not being in the range of 5 years to 20 years, try again with a range between 5-20 years.";
			}
			else {
				loanRepository.save(loan);
				return "Congratulations. Loan Approved";
			}
			

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "Invalid Credentials. Please retry";
        }

	}

		public String calculateEMI(Long LoanId) {
			// EMI = [P x R x (1+R)^N]/[(1+R)^N-1]
			
			Optional<Loan> l= loanRepository.findById(LoanId);
			if(l.isPresent()) {
				Long P, N;
				double emi, R;
				
				P = l.get().getTotalLoanAmount();
				N = l.get().getTenure()*12;
				R = l.get().getInterestRate()/1200; 
				
				emi = (P*R*Math.pow(1+R, N))/(Math.pow(1+R, N)-1);
				
				return "The amount to be paid as EMI for thr given loan:  "+ emi;
			}
			return "Sorry, we didn't find any loan for the given ID.";
	}
	
}
