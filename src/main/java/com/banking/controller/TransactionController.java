package com.banking.controller;
import com.banking.entity.Transaction;
import com.banking.service.TransactionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")

public class TransactionController {
	
	private final TransactionService transactionService;
	
	public TransactionController(TransactionService  transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping
	public Transaction createTransaction(@RequestBody Transaction transaction) {
		return transactionService.createTransaction(transaction);
	}
	@GetMapping("/account/{accountId}")
	public List<Transaction> getTransactionByAccountId(
			@PathVariable Long accountId){
		return transactionService.getTransactionsByAccountId(accountId);
	}

}
