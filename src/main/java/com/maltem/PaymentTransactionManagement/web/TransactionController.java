package com.maltem.PaymentTransactionManagement.web;

import com.maltem.PaymentTransactionManagement.dao.dto.TransactionInput;
import com.maltem.PaymentTransactionManagement.dao.dto.TransactionOutput;
import com.maltem.PaymentTransactionManagement.dao.dto.TransactionsOutput;
import com.maltem.PaymentTransactionManagement.service.services.TransactionService;
import com.maltem.PaymentTransactionManagement.service.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    public final TransactionService transactionService;

   public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/save")
    private TransactionOutput saveCustomer(@RequestBody TransactionInput transactionInput){
        return  transactionService.saveTransaction(transactionInput);
    }

    @PutMapping("/{id}/process")
    private TransactionOutput processTransaction(@RequestBody TransactionInput transactionInput, @PathVariable String id){
        return transactionService.processTransaction(transactionInput, id);
    }

    @GetMapping("/find/{id}")
    private TransactionOutput getTransaction(@PathVariable String id){
        return transactionService.getTransaction(id);
    }

    @GetMapping
    public TransactionsOutput getTransactions(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {


        return  transactionService.getTransactions(status, accountId, customerId, startDate, endDate);
    }


    @RequestMapping("/delete/{id}")
    private void deleteTransaction(@PathVariable String id){
        transactionService.deleteTransaction(id);
    }

}
