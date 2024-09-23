package com.maltem.PaymentTransactionManagement.web;

import com.maltem.PaymentTransactionManagement.dao.dto.AccountInput;
import com.maltem.PaymentTransactionManagement.dao.dto.AccountOutput;
import com.maltem.PaymentTransactionManagement.service.services.AccountService;
import com.maltem.PaymentTransactionManagement.service.services.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    public final AccountService accountService;

   public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }



    @GetMapping("/find/{id}")
    private AccountOutput getAccount(@PathVariable String id){
        return accountService.getAccount(id);
    }

    @PostMapping("/save")
    private AccountOutput saveCustomer(@RequestBody AccountInput accountInput){
        return  accountService.saveAccount(accountInput);
    }

    @PutMapping("/update/{id}")
    private AccountOutput updateAccount(@RequestBody AccountInput accountInput, @PathVariable String id){
        return accountService.updateAccount(accountInput, id);
    }

    @RequestMapping("/delete/{id}")
    private void deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
    }
}
