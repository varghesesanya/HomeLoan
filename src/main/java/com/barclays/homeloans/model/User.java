package com.barclays.homeloans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;

@Entity
public class User {
	@Column
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Customerid;
	
	@Column
	@NotNull(message = "Name cannot be Null")
	private String name;
	
	@Column
	@NotNull(message = "Email cannot be Null")
	private String email;
	
	@Column
	@NotNull(message = "Password cannot be Null")
	private String password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String email, String password) {
		super();
		
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Long getCustomerid() {
		return Customerid;
	}

	public void setCustomerid(Long customerid) {
		Customerid = customerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [Customerid=" + Customerid + ", name=" + name + ", email=" + email + ", password=" + password
				+ "]";
	}
	
	
	
}
