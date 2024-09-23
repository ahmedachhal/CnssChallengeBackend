package com.maltem.PaymentTransactionManagement.dao.dto;

import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.entities.PaymentMethod;
import com.maltem.PaymentTransactionManagement.dao.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerOutput {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private Date createdDate;
    private List<Account> accountsList;
    private List<PaymentMethod> paymentMethodList;
}
