package com.banking.service;
import com.banking.entity.Transaction;
import java.util.List;



public interface TransactionService {
	Transaction createTransaction(Transaction transaction);
	
	List<Transaction> getTransactionsByAccountId(Long  accountId);

}
