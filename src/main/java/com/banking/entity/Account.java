package com.banking.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")

public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String accountNumber;
	
	@Column(nullable = false)
	private String accountType;
	
	@Column(nullable = false)
	private BigDecimal  balance =BigDecimal.ZERO;
	
	@Column(nullable = false)
	private String status = "ACTIVE";
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	public Account() {
		
	}
	public Long	 getId() {
		return id;
	}
	public void setId(Long id) {
		this.id= id;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		
	}

}
