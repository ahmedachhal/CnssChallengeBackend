package com.maltem.PaymentTransactionManagement.dao.dto;

import com.maltem.PaymentTransactionManagement.dao.entities.Customer;
import com.maltem.PaymentTransactionManagement.dao.entities.Transaction;
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
public class PaymentMethodInput {
    private String id;
    private String type;
    private String provider;
    private String accountNumber;
    private Date expiryDate;
    private Date createdDate;
    private String status;
    private Customer customer;
    private List<Transaction> transactionsList;
}
