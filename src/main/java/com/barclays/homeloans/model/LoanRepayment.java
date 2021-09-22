package com.barclays.homeloans.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;



@Entity
public class LoanRepayment {
	
	@Column
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanRepaymentId;
		
	@Column
	@NotNull(message = "EMI cannot be Null")
	private double emi;
	
	
	@Column
	@NotNull(message = "Interest Amount cannot be Null")
	private double interestAmount;
	
	@Column
	@NotNull(message = "Principal Amount cannot be Null")
	private double principalAmount;
	
	@Column
	@NotNull(message = "Status cannot be Null")
	private String status;
	
	@Column
	@NotNull(message = "Outstanding cannot be Null")
	private double outstanding;
	
	@Column
	@NotNull(message = "Date cannot be Null")
	private Date date;

	public LoanRepayment(@NotNull(message = "EMI cannot be Null") double emi,
			@NotNull(message = "Interest Amount cannot be Null") double interestAmount,
			@NotNull(message = "Principal Amount cannot be Null") double principalAmount,
			@NotNull(message = "Status cannot be Null") String status,
			@NotNull(message = "Outstanding cannot be Null") double outstanding,
			@NotNull(message = "Date cannot be Null") Date date) {
		super();
		this.emi = emi;
		this.interestAmount = interestAmount;
		this.principalAmount = principalAmount;
		this.status = status;
		this.outstanding = outstanding;
		this.date = date;
	}

	public LoanRepayment() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	public Long getLoanRepaymentId() {
		return loanRepaymentId;
	}

	public void setLoanRepaymentId(Long loanRepaymentId) {
		this.loanRepaymentId = loanRepaymentId;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(double outstanding) {
		this.outstanding = outstanding;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "LoanRepayment [loanRepaymentId=" + loanRepaymentId + ", emi=" + emi + ", interestAmount="
				+ interestAmount + ", principalAmount=" + principalAmount + ", status=" + status + ", outstanding="
				+ outstanding + ", date=" + date + "]";
	}




}
