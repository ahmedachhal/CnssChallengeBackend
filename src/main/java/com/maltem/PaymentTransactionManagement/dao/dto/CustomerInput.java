package com.maltem.PaymentTransactionManagement.dao.dto;

import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerInput {
     private String id;
     private String name;
     private String email;
     private String phone;
     private String status;
     private List<Account> accountsList;
}
