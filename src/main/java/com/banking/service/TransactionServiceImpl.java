package com.banking.service;
import com.banking.entity.Transaction;
import com.banking.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository= transactionRepository;
	}
	@Override
	public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	@Override
	public List<Transaction> getTransactionsByAccountId(Long accountId){
		return transactionRepository.findAll()
				.stream()
				.filter(transaction ->
				           transaction.getAccount().getId().equals(accountId))
				 .toList();
	}

}
