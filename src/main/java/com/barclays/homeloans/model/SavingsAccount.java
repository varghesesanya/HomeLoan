package com.barclays.homeloans.model;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
public class SavingsAccount {

	@Id
	@Column
	@NotNull(message = "Account Number cannot be Null")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="store-sequence-generator")
	@SequenceGenerator(name = "store-sequence-generator", sequenceName = "acc_no_sequence", initialValue = 1000000000  , allocationSize=1)
	private Long accountNumber;
	
	@Column
	@NotNull(message = "Balance cannot be Null")
	private Long balance;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@NotNull(message = "User cannot be Null")
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
	

//	public Long getSequenceAccountId() {
//		return sequenceAccountId;
//	}
//
//	public void setSequenceAccountId(Long sequenceAccountId) {
//		this.sequenceAccountId = sequenceAccountId;
//	}

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
		return "SavingsAccount [sequenceAccountId=" /*+ sequenceAccountId*/ + ", accountNumber=" + accountNumber
				+ ", balance=" + balance + ", user=" + user + "]";
	}
	



}
