package com.maltem.PaymentTransactionManagement.dao.entities;

import com.maltem.PaymentTransactionManagement.dao.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMethod {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    private String provider;
    private String accountNumber;
    private Date expiryDate;
    private Date createdDate;
    private PaymentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private List<Transaction> transactionsList;

    @PrePersist
    private void onPrePersist(){
        if(this.createdDate == null){
            this.createdDate = new Date();
        }
    }
}
