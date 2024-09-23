package com.maltem.PaymentTransactionManagement.dao.repository;

import com.maltem.PaymentTransactionManagement.dao.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
