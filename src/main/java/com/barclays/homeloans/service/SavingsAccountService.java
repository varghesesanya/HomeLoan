package com.barclays.homeloans.service;

import com.barclays.homeloans.model.SavingsAccount;
import com.barclays.homeloans.model.User;
import com.barclays.homeloans.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SavingsAccountService {

	@Autowired
    private SavingsAccountRepository savingsAccRepo;

    public void createNewAccount(User user, Long initialBalance) {
        SavingsAccount account = new SavingsAccount();
        account.setUser(user);
        account.setBalance(initialBalance);
        savingsAccRepo.save(account);
    }
}
