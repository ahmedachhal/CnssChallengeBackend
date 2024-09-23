package com.maltem.PaymentTransactionManagement.service.mappers;

import com.maltem.PaymentTransactionManagement.dao.dto.AccountInput;
import com.maltem.PaymentTransactionManagement.dao.dto.AccountOutput;
import com.maltem.PaymentTransactionManagement.dao.dto.TransactionInput;
import com.maltem.PaymentTransactionManagement.dao.dto.TransactionOutput;
import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


    TransactionOutput fromDbToOutput(Transaction transaction);
    List<TransactionOutput> fromDbToOutput(List<Transaction> transactions);

    @Mapping(target = "status", ignore = true)
    Transaction fromInputToDb(TransactionInput transactionInput);
    List<Transaction> fromInputToDb(List<TransactionInput> transactionInputs);
}
