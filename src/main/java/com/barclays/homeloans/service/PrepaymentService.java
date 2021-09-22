package com.barclays.homeloans.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.model.LoanRepayment;
import com.barclays.homeloans.model.User;
import com.barclays.homeloans.repository.LoanRepaymentRepository;
import com.barclays.homeloans.repository.LoanRepository;

@Repository
public class PrepaymentService {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private LoanRepaymentRepository loanRepaymentRepository;
	private Loan loan;
///***LOAN --> TRIGGER REPAYMENT
//// SET FORM PREVIOUS	
	
	// PREPAYMENT
	public String prepayment(Long loan_id, Long amount) {
		
		Optional <Loan> loan_list  = loanRepository.findById(loan_id);
		List <LoanRepayment> emi_list = loan_list.get().getLoanRepayment();
		
		// OUTSTANDING EMI CALCULATION
		LoanRepayment last_outstanding = emi_list.get(emi_list.size() - 1);
		
		Long N;
		double emi=0, R, P;
		LoanRepayment prepayment_emi = new LoanRepayment();
		Date now = new Date();  
		now.setMonth(now.getMonth()+1);
		
		if(amount>=3*last_outstanding.getEmi() && !loan_list.get().getStatus().equals("Closed")){
			
			N = loan_list.get().getTenure()*12;
			P = last_outstanding.getOutstanding() - amount;

			if(P <=0) {
				prepayment_emi.setEmi(0);
				prepayment_emi.setInterestAmount(0);
				prepayment_emi.setOutstanding(0);
				prepayment_emi.setPrincipalAmount(0);
				prepayment_emi.setStatus("Paid");
				prepayment_emi.setDate(now);
				
				loan_list.get().setStatus("Closed");
			}
			else {
				R = loan_list.get().getInterestRate()/1200; 
				emi = (P*R*Math.pow(1+R, N))/(Math.pow(1+R, N)-1);
			
				prepayment_emi.setEmi(emi);
				prepayment_emi.setInterestAmount(R*P);
				prepayment_emi.setOutstanding(P);
				prepayment_emi.setPrincipalAmount( last_outstanding.getEmi()- (R*P));
				prepayment_emi.setStatus("Pending");
				prepayment_emi.setDate(now);
			
			}
			last_outstanding.setStatus("Paid");
			loanRepaymentRepository.save(prepayment_emi);	
			System.out.println(prepayment_emi);
			emi_list.add(prepayment_emi);
			

			if (loan_list.isPresent())
			{
				Loan loan= loan_list.get();
				loan.setLoanRepayment(emi_list);
				loanRepository.save(loan);
				return "NEW EMI UPDATED: " + emi;
			}
			return "EMI REMAINS SAME";

			}
		return "DOES NOT SATISFY";
	}
	
	
	
	/// FORECLOSURE
	public String foreclosure(Long loan_id) {	
		Optional <Loan> loan_list  = loanRepository.findById(loan_id);
		
		if (loan_list.isPresent()){
			Loan loan= loan_list.get();
			List <LoanRepayment> emi_list = loan.getLoanRepayment();
			if(loan.getStatus().equals("Closed")) {
				return "LOAN ALREADY PAID";
			}
			else if(emi_list.size()>=4 ){
				LoanRepayment last_emi = emi_list.get(emi_list.size() - 1);
				System.out.println(last_emi);

				System.out.println(loan.getStatus());
				LoanRepayment foreclosure_emi = new LoanRepayment();
				
				Date now = new Date();  
				now.setMonth(now.getMonth()+1);
				
				
				foreclosure_emi.setEmi(0);
				foreclosure_emi.setInterestAmount(0);
				foreclosure_emi.setOutstanding(0);
				foreclosure_emi.setPrincipalAmount(0);
				foreclosure_emi.setStatus("Paid");
				foreclosure_emi.setDate(now);
				
				//***5
				LoanRepayment last_outstanding = emi_list.get(emi_list.size() - 1);
				last_outstanding.setStatus("Paid");
				
				loanRepaymentRepository.save(foreclosure_emi);	
				System.out.println(foreclosure_emi);
				
				emi_list.add(foreclosure_emi);
				
				loan.setLoanRepayment(emi_list);
				loan.setStatus("Closed");
				
				loanRepository.save(loan);	
				return "LOAN SET TO BE PAID";
			}
		}
		else {
			return "LOAN ID NOT PRESENT";
		}
		return "LOAN ID NOT PRESENT";
				
		}
}
