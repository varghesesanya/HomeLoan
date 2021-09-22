package com.barclays.homeloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.homeloans.model.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

}
