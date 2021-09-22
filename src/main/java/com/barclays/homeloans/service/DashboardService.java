package com.barclays.homeloans.service;
import java.io.File;
import java.util.List;
import java.util.Optional;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.model.LoanRepayment;
import com.barclays.homeloans.repository.LoanRepository;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

@Repository
public class DashboardService {
	@Autowired
	private LoanRepository loanRepository;
	

	public List <LoanRepayment> Dashboard(Long loan_id) {

		Optional <Loan> loan_list  = loanRepository.findById(loan_id);
		if(loan_list.isPresent()) {
			List <LoanRepayment> emi_list = loan_list.get().getLoanRepayment();
			return emi_list;
		}
		return null;
		}
	}
