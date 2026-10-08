package com.banking.controller;
import com.banking.entity.Account;
import com.banking.service.AccountService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {
	
	private final AccountService accountService;
	
	public AccountController (AccountService accountService) {
		this.accountService = accountService;
	}
	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	@PostMapping("/{id}/deposit")
	public Account depositMoney(
			@PathVariable Long id,
			@RequestParam BigDecimal amount) {
		return accountService.depositMoney(id, amount);
	}
	@PostMapping("/{id}/withdraw")
	public Account withdrawMoney(
			@PathVariable Long id,
			@RequestParam BigDecimal amount) {
		return accountService.withdrawMoney(id, amount);
	}
	@PostMapping("/transfer")
	public Account transferMoney(
			@RequestParam Long fromAccountId,
			@RequestParam Long toAccountId,
			@RequestParam BigDecimal amount) {
		return accountService.transferMoney(fromAccountId, toAccountId, amount);
	}
	
	@GetMapping
	public  List<Account> getAllAccount(){
		return accountService.getAllAccount();
	}
	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable Long id) {
		return  accountService.getAccountById(id);
	}
	
	@GetMapping("/number/{accountNumber}")
	public Account getAccountByNumber(@PathVariable String  accountNumber) {
		return accountService.getAccountByNumber(accountNumber);
	}
	@PutMapping("/{id}")
	public Account updateAccount(
	        @PathVariable Long id,
	        @RequestBody Account account) {

	    return accountService.updateAccount(id, account);
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteAccount(@PathVariable Long id) {

	    accountService.deleteAccount(id);

	    return "Account deleted successfully";
	}

}
