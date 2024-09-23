package com.maltem.PaymentTransactionManagement.web;

import com.maltem.PaymentTransactionManagement.batch.serviceBatch.BatchPaymentOutput;
import com.maltem.PaymentTransactionManagement.batch.serviceBatch.PaymentService;
import com.maltem.PaymentTransactionManagement.dao.dto.TransactionInput;
import com.maltem.PaymentTransactionManagement.dao.dto.TransactionOutput;
import com.maltem.PaymentTransactionManagement.dao.dto.TransactionsOutput;
import com.maltem.PaymentTransactionManagement.service.services.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class TransactionBatchController {

    public final PaymentService paymentService;

   public TransactionBatchController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{id}/batch-process")
    public BatchPaymentOutput processBatchPayments(@PathVariable String id) {
        return paymentService.processBatchPayments(id);
    }
}
