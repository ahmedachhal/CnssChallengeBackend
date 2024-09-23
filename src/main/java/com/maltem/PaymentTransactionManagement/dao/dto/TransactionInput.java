package com.maltem.PaymentTransactionManagement.dao.dto;

import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.entities.Customer;
import com.maltem.PaymentTransactionManagement.dao.entities.PaymentMethod;
import com.maltem.PaymentTransactionManagement.dao.entities.Transaction;
import com.maltem.PaymentTransactionManagement.dao.enums.TransactionsStatus;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionInput {
     private String id;
     private Double amount;
     private String currency;
     private String description;
     private Date transactionDate;
     private String transactionStatus;
     private Account account;
     private PaymentMethod paymentMethod;


}
