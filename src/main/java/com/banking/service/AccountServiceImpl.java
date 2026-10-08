package com.banking.service;

import com.banking.entity.Account;
import com.banking.entity.Customer;
import com.banking.entity.Transaction;
import com.banking.exception.AccountException;
import com.banking.repository.AccountRepository;
import com.banking.repository.CustomerRepository;
import com.banking.repository.TransactionRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              CustomerRepository customerRepository,
                              TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account createAccount(Account account) {

        Long customerId = account.getCustomer().getID();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                         new AccountException("Customer not found with ID: " + customerId));

        account.setCustomer(customer);

        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountException("Account not found with ID: " + id));
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {

        return accountRepository.findAll()
                .stream()
                .filter(account ->
                        account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() ->
                        new AccountException  (
                                "Account not found with account number: "
                                        + accountNumber));
    }

    @Override
    public Account updateAccount(Long id, Account account) {

        Account existingAccount = getAccountById(id);

        existingAccount.setAccountNumber(account.getAccountNumber());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setStatus(account.getStatus());

        return accountRepository.save(existingAccount);
    }

    @Override
    public Account depositMoney(Long id, BigDecimal amount) {

        Account account = getAccountById(id);

        if (!account.getStatus().equals("ACTIVE")) {
        	throw new AccountException("Account is not active");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
        	throw new AccountException("Deposit amount must be greater than zero");
        }

        account.setBalance(account.getBalance().add(amount));

        accountRepository.save(account);

        Transaction transaction = new Transaction();

        transaction.setAccount(account);
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setTransactionDate(
                java.time.LocalDateTime.now());
        transaction.setDescription(
                "Money deposited into account");

        transactionRepository.save(transaction);

        return account;
    }

    @Override
    public Account withdrawMoney(Long id, BigDecimal amount) {

        Account account = getAccountById(id);

        if (!account.getStatus().equals("ACTIVE")) {
        	throw new AccountException("Account is not active");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
        	throw new AccountException("Withdraw amount must be greater than zero");
        }

        if (account.getBalance().compareTo(amount) < 0) {
        	throw new AccountException("Insufficient balance");
        }

        account.setBalance(
                account.getBalance().subtract(amount));

        accountRepository.save(account);

        Transaction transaction = new Transaction();

        transaction.setAccount(account);
        transaction.setTransactionType("WITHDRAW");
        transaction.setAmount(amount);
        transaction.setTransactionDate(
                java.time.LocalDateTime.now());
        transaction.setDescription(
                "Money withdrawn from account");

        transactionRepository.save(transaction);

        return account;
    }

    @Override
    @Transactional
    public Account transferMoney(Long fromAccountId,
                                 Long toAccountId,
                                 BigDecimal amount) {

        Account fromAccount = getAccountById(fromAccountId);
        Account toAccount = getAccountById(toAccountId);
        
        if (!fromAccount.getStatus().equals("ACTIVE")) {
        	throw new AccountException("Source account is not active");
        }

        if (!toAccount.getStatus().equals("ACTIVE")) {
        	throw new AccountException("Destination account is not active");
        }

        if (fromAccountId.equals(toAccountId)) {
        	throw new AccountException("Cannot transfer money to the same account");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
        	throw new AccountException("Transfer amount must be greater than zero");
        }

        if (fromAccount.getBalance().compareTo(amount) < 0) {
        	throw new AccountException("Insufficient balance for transfer");
        }

        fromAccount.setBalance(
                fromAccount.getBalance().subtract(amount));

        toAccount.setBalance(
                toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // TRANSFER OUT
        Transaction transferOut = new Transaction();

        transferOut.setAccount(fromAccount);
        transferOut.setTransactionType("TRANSFER_OUT");
        transferOut.setAmount(amount);
        transferOut.setTransactionDate(
                java.time.LocalDateTime.now());
        transferOut.setDescription(
                "Money transferred to account "
                        + toAccount.getAccountNumber());

        transactionRepository.save(transferOut);

        // TRANSFER IN
        Transaction transferIn = new Transaction();

        transferIn.setAccount(toAccount);
        transferIn.setTransactionType("TRANSFER_IN");
        transferIn.setAmount(amount);
        transferIn.setTransactionDate(
                java.time.LocalDateTime.now());
        transferIn.setDescription(
                "Money received from account "
                        + fromAccount.getAccountNumber());

        transactionRepository.save(transferIn);

        return fromAccount;
    }

    @Override
    public void deleteAccount(Long id) {

        Account existingAccount = getAccountById(id);

        accountRepository.delete(existingAccount);
    }
}