package com.barclays.homeloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.barclays.homeloans.model.LoanRepayment;


public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, Long>{
//@Query("SELECT e FROM Loan e where e.loanId =?1")
//List <LoanRepayment> getAllEmi(Long loan_id);
}
