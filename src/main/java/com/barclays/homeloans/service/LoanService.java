package com.barclays.homeloans.service;

import java.util.List;
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
	
	public boolean loanApplication(Loan loan, Integer monthlySalary) {
		try {
			if(monthlySalary*50<= loan.getTotalLoanAmount()) {
				return false;
			}
			else if(5*12>loan.getTenure() || 20*12<loan.getTenure()){
				return false;
			}
			else {
				loanRepository.save(loan);
				return true;
			}
			

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

	}

		public double calculateEMI(Long LoanId) {
			// EMI = [P x R x (1+R)^N]/[(1+R)^N-1]
			
			Optional<Loan> l= loanRepository.findById(LoanId);
			if(l.isPresent()) {
				Long P, N;
				double emi, R;
				
				P = l.get().getTotalLoanAmount();
				N = l.get().getTenure()*12;
				R = l.get().getInterestRate()/1200; 
				
				emi = (P*R*Math.pow(1+R, N))/(Math.pow(1+R, N)-1);
				
				return emi;
			}
			return 0;
	}
		
		public Loan insertLoan(Loan loan){
			
			return loanRepository.save(loan);
			
		}
		
		public double calculateInterest(double outstanding,double rate){
			
			return (outstanding*rate)/1200;
			
		}
		
		
		public List<Loan> getLoans()
		{
			return loanRepository.getLoans();
		}
	
}
