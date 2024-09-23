package com.maltem.PaymentTransactionManagement.service.services;

import com.maltem.PaymentTransactionManagement.dao.dto.*;
import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.entities.Transaction;
import com.maltem.PaymentTransactionManagement.dao.enums.AccountStatus;
import com.maltem.PaymentTransactionManagement.dao.enums.CustomerStatus;
import com.maltem.PaymentTransactionManagement.dao.enums.TransactionsStatus;
import com.maltem.PaymentTransactionManagement.dao.repository.AccountRepository;
import com.maltem.PaymentTransactionManagement.dao.repository.TransactionRepository;
import com.maltem.PaymentTransactionManagement.service.Exception.AccountNotFoundException;
import com.maltem.PaymentTransactionManagement.service.Exception.CustomerNotFoundException;
import com.maltem.PaymentTransactionManagement.service.Exception.PaymentMethodNotFoundException;
import com.maltem.PaymentTransactionManagement.service.Exception.TransactionNotFoundException;
import com.maltem.PaymentTransactionManagement.service.mappers.AccountMapper;
import com.maltem.PaymentTransactionManagement.service.mappers.TransactionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final AccountService        accountService;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper     transactionMapper;

    private final PaymentMethodService  paymentMethodService;

    public TransactionService(AccountService accountService, TransactionRepository transactionRepository, TransactionMapper transactionMapper, PaymentMethodService paymentMethodService) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.paymentMethodService = paymentMethodService;
    }

    public TransactionOutput getTransaction(String id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        TransactionOutput result = transactionMapper.fromDbToOutput(transaction);
        return  result;
    }

    public TransactionOutput saveTransaction(TransactionInput transactionInput) {
        //check account
        if(transactionInput.getAccount() != null ){
            AccountOutput accountOutput = accountService.getAccount(transactionInput.getAccount().getId());
            if(accountOutput == null) throw new AccountNotFoundException("Account not found with ID: " + transactionInput.getAccount().getId());
        }else{
            throw new AccountNotFoundException("AUnable to create a transaction with a null account ");
        }
        //checkPaymentMethod
        if(transactionInput.getPaymentMethod() != null ){
            PaymentMethodOutput paymentMethodOutput = paymentMethodService.getPaymentMethod(transactionInput.getPaymentMethod().getId());
            if(paymentMethodOutput == null) throw new PaymentMethodNotFoundException("Account not paymentMethod with ID: " + transactionInput.getPaymentMethod().getId());
        }else{
            throw new PaymentMethodNotFoundException("Unable to create a transaction with a null paymentMethod ");
        }

        // Create transaction
        Transaction transaction = transactionMapper.fromInputToDb(transactionInput);
        //check amount
        transaction.setTransactionStatus(transaction.getAmount() > 0 ? TransactionsStatus.PENDING : TransactionsStatus.FAILED);
        Transaction transactionDb = transactionRepository.save(transaction);
        TransactionOutput result = transactionMapper.fromDbToOutput(transactionDb);
        return result;
    }


    public void deleteTransaction(String id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        if(transaction.getTransactionStatus() != TransactionsStatus.PENDING) throw  new TransactionNotFoundException(" Failed to canceled transaction");
        transaction.setTransactionStatus(TransactionsStatus.CANCELLED);
        transactionRepository.saveAndFlush(transaction);
    }

    public TransactionOutput processTransaction(TransactionInput transactionInput, String id) {
        Transaction transaction = transactionMapper.fromInputToDb(transactionInput);
        TransactionsStatus transactionsStatus = TransactionsStatus.enumFromString(transactionInput.getTransactionStatus());
        //check transaction if pending
        if(transactionsStatus != TransactionsStatus.PENDING) throw  new TransactionNotFoundException(" Failed to processed transaction");
        // check amount
        transaction.setTransactionStatus(transaction.getAmount() > 0 ? TransactionsStatus.COMPLETED : TransactionsStatus.FAILED);
        transaction.setTransactionDate(new Date());
        Transaction newTransactionDb = transactionRepository.saveAndFlush(transaction);
        TransactionOutput result = transactionMapper.fromDbToOutput(newTransactionDb);
        return result;
    }


    public TransactionsOutput getTransactions(String status, Long accountId, Long customerId, String startDate, String endDate) {
       //cast date
        LocalDate start = (startDate != null) ? LocalDate.parse(startDate) : null;
        LocalDate end = (endDate != null) ? LocalDate.parse(endDate) : null;
        // search by filter
        List<Transaction> transactions = transactionRepository.findTransactions(status, accountId, customerId, start, end);

        return new TransactionsOutput(transactions);
    }
}
