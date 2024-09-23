package com.maltem.PaymentTransactionManagement.dao.entities;

import com.maltem.PaymentTransactionManagement.dao.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountNumber;
    private Double balance;
    private String accountType;
    private String currency;
    private Date createdDate;
    private Date processedDate;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactionsList;

    @PrePersist
    private void onPrePersist(){
        if(this.createdDate == null){
           this.createdDate = new Date();
        }
    }

}

