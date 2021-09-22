package com.barclays.homeloans.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import javax.validation.constraints.NotNull;

@Entity
public class SavingsAccount {
	@Column
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sequenceAccountId;
	
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Account Number cannot be Null")
	private Long accountNumber;
	
	@Column
	@NotNull(message = "Balance cannot be Null")
	private Long balance;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public SavingsAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(@NotNull(message = "Balance cannot be Null") Long balance, User user) {
		super();
		this.balance = balance;
		this.user = user;
	}
	

	public Long getSequenceAccountId() {
		return sequenceAccountId;
	}

	public void setSequenceAccountId(Long sequenceAccountId) {
		this.sequenceAccountId = sequenceAccountId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SavingsAccount [sequenceAccountId=" + sequenceAccountId + ", accountNumber=" + accountNumber
				+ ", balance=" + balance + ", user=" + user + "]";
	}
	



}
