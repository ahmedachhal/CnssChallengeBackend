package com.maltem.PaymentTransactionManagement.dao.entities;

import com.maltem.PaymentTransactionManagement.dao.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String phone;
    private Date createdDate;
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accountsList;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<PaymentMethod> paymentMethodList;

    @PrePersist
    private void onPrePersist(){
        if(this.createdDate == null){
            this.createdDate = new Date();
        }
    }
}