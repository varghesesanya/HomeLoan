package com.barclays.homeloans.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Loan {
	
	@Column
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
	
	
	@Column
	@NotNull(message = "Total Loan Amount cannot be Null")
	private Long totalLoanAmount;
	
	
	@Column
	@NotNull(message = "Interest Rate cannot be Null")
	private double interestRate;
	
	@Column
	@NotNull(message = "Tenure cannot be Null")
	private Long tenure;
	
	@Column
	@NotNull(message = "Status cannot be Null")
	private String status;
	
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	@Column
    private byte[] propertyImage;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private SavingsAccount savingsAccount;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="loanId")
	private List<LoanRepayment> loanRepayment;

	
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Loan(@NotNull(message = "Total Loan Amount cannot be Null") Long totalLoanAmount,
			@NotNull(message = "Interest Rate cannot be Null") double interestRate,
			@NotNull(message = "Tenure cannot be Null") Long tenure,
			@NotNull(message = "Status cannot be Null") String status, byte[] propertyImage,
			SavingsAccount savingsAccount, List<LoanRepayment> loanRepayment) {
		super();
		this.totalLoanAmount = totalLoanAmount;
		this.interestRate = interestRate;
		this.tenure = tenure;
		this.status = status;
		this.propertyImage = propertyImage;
		this.savingsAccount = savingsAccount;
		this.loanRepayment = loanRepayment;
	}


	public Long getLoanId() {
		return loanId;
	}


	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}


	public Long getTotalLoanAmount() {
		return totalLoanAmount;
	}


	public void setTotalLoanAmount(Long totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}


	public double getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}


	public Long getTenure() {
		return tenure;
	}


	public void setTenure(Long tenure) {
		this.tenure = tenure;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public byte[] getPropertyImage() {
		return propertyImage;
	}


	public void setPropertyImage(byte[] propertyImage) {
		this.propertyImage = propertyImage;
	}


	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}


	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}


	public List<LoanRepayment> getLoanRepayment() {
		return loanRepayment;
	}


	public void setLoanRepayment(List<LoanRepayment> loanRepayment) {
		this.loanRepayment = loanRepayment;
	}


	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", totalLoanAmount=" + totalLoanAmount + ", interestRate=" + interestRate
				+ ", tenure=" + tenure + ", status=" + status + ", propertyImage=" + Arrays.toString(propertyImage)
				+ ", savingsAccount=" + savingsAccount + ", loanRepayment=" + loanRepayment + "]";
	}

	
}
