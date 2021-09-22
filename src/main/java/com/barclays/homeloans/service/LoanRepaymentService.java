package com.barclays.homeloans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.homeloans.model.LoanRepayment;
import com.barclays.homeloans.repository.LoanRepaymentRepository;

@Repository
public class LoanRepaymentService {

	@Autowired
	private LoanRepaymentRepository loanRepaymentRepository;
	
	public void createLoanRepayment(LoanRepayment loanRepayment)
	{
		loanRepaymentRepository.save(loanRepayment);
	}

}
