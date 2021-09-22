package com.barclays.homeloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.barclays.homeloans.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
	
	@Query("FROM Loan l WHERE l.status='Ongoing'")
	public List<Loan> getLoans();
	
	
}
	