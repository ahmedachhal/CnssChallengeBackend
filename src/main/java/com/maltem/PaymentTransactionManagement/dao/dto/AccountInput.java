package com.maltem.PaymentTransactionManagement.dao.dto;

import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.entities.Customer;
import com.maltem.PaymentTransactionManagement.dao.entities.Transaction;
import com.maltem.PaymentTransactionManagement.dao.enums.AccountStatus;
import jakarta.persistence.*;
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
public class AccountInput {
     private String id;
     private String accountNumber;
     private Double balance;
     private String accountType;
     private String currency;
     private Date createdDate;
     private String status;
     private Customer customer;
     private List<Transaction> transactionsList;

}
