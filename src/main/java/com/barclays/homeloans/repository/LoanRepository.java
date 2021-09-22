package com.barclays.homeloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.homeloans.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
}
