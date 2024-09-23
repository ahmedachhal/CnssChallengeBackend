package com.maltem.PaymentTransactionManagement.dao.repository;


import com.maltem.PaymentTransactionManagement.dao.dto.TransactionsOutput;
import com.maltem.PaymentTransactionManagement.dao.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t WHERE (:status IS NULL OR t.status = :status) " +
            "AND (:accountId IS NULL OR t.account.id = :accountId) " +
            "AND (:customerId IS NULL OR t.customer.id = :customerId) " +
            "AND (:startDate IS NULL OR t.date >= :startDate) " +
            "AND (:endDate IS NULL OR t.date <= :endDate)")
    List<Transaction> findTransactions(
            @Param("status") String status,
            @Param("accountId") Long accountId,
            @Param("customerId") Long customerId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
