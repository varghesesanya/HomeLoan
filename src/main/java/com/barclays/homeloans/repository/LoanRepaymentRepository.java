package com.barclays.homeloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.homeloans.model.LoanRepayment;

public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, Long>{
	
	

}
