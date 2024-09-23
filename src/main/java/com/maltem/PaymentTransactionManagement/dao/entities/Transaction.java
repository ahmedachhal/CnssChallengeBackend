package com.maltem.PaymentTransactionManagement.dao.entities;

import com.maltem.PaymentTransactionManagement.dao.enums.TransactionsStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double amount;
    private String currency;
    private String description;
    private Date transactionDate;
    private TransactionsStatus transactionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;

    @PrePersist
    private void onPrePersist(){
        if(this.transactionDate == null){
            this.transactionDate = new Date();
        }
    }

}
