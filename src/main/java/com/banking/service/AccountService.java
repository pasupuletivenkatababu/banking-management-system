package com.banking.service;
import com.banking.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
	
	Account createAccount(Account account);
	
	List<Account> getAllAccount();
	
	Account getAccountById(Long id);
	
	Account getAccountByNumber(String accountNumber);
	
	
	Account updateAccount(Long id, Account account);
	
	Account depositMoney(Long id, BigDecimal amount);
	
	Account withdrawMoney(Long id, BigDecimal amount);
	
	Account transferMoney(Long id, Long toAccountId, BigDecimal amount);
	
	void deleteAccount(Long id);

}
