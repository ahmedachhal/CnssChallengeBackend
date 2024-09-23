package com.maltem.PaymentTransactionManagement.service.services;

import com.maltem.PaymentTransactionManagement.dao.dto.AccountInput;
import com.maltem.PaymentTransactionManagement.dao.dto.AccountOutput;
import com.maltem.PaymentTransactionManagement.dao.dto.CustomerOutput;
import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.entities.Customer;
import com.maltem.PaymentTransactionManagement.dao.enums.AccountStatus;
import com.maltem.PaymentTransactionManagement.dao.enums.CustomerStatus;
import com.maltem.PaymentTransactionManagement.dao.repository.AccountRepository;
import com.maltem.PaymentTransactionManagement.service.Exception.CustomerNotFoundException;
import com.maltem.PaymentTransactionManagement.service.mappers.AccountMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository     accountRepository;
    private final AccountMapper         accountMapper;
    private final CustomerService       customerService;


    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.customerService = customerService;
    }

    public AccountOutput saveAccount(AccountInput accountInput) {
        // check customer
        if(accountInput.getCustomer() != null){
            CustomerOutput customer = customerService.getCustomer(accountInput.getCustomer().getId());
            if(customer == null )  throw new CustomerNotFoundException("Customer not found with ID: " + accountInput.getCustomer().getId());
        }else{
             throw new CustomerNotFoundException("Unable to create a PaymentMethod with a null customer ");
        }
        Account account = accountMapper.fromInputToDb(accountInput);
        AccountStatus accountStatus = AccountStatus.enumFromString(accountInput.getStatus());
        account.setStatus(accountStatus);
        Account accountDb = accountRepository.save(account);
        AccountOutput accountOutput = accountMapper.fromDbToOutput(accountDb);
        return accountOutput;

    }

    public AccountOutput updateAccount(AccountInput accountInput, String id) {

        Account newAccount = accountMapper.fromInputToDb(accountInput);
        Account oldAccount = accountRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(newAccount, oldAccount, "id" , "createdDate","accountNumber","currency","balance","customer");
        Account accountNewDb = accountRepository.saveAndFlush(oldAccount);
        AccountOutput accountOutput = accountMapper.fromDbToOutput(accountNewDb);
        return accountOutput;

    }

    public void deleteAccount(String id) {
        Account accountdb = accountRepository.findById(id).orElseThrow();
        accountdb.setStatus(AccountStatus.CLOSED);
        accountRepository.save(accountdb);
    }

    public AccountOutput getAccount(String id) {
        Account accountdb = accountRepository.findById(id).orElse(null);
        if(accountdb == null) return null;
        AccountOutput accountOutput = accountMapper.fromDbToOutput(accountdb);
        return accountOutput;
    }
}
