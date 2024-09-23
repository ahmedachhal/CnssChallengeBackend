package com.maltem.PaymentTransactionManagement.service.mappers;

import com.maltem.PaymentTransactionManagement.dao.dto.AccountInput;
import com.maltem.PaymentTransactionManagement.dao.dto.AccountOutput;
import com.maltem.PaymentTransactionManagement.dao.dto.CustomerInput;
import com.maltem.PaymentTransactionManagement.dao.dto.CustomerOutput;
import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.entities.Customer;
import com.maltem.PaymentTransactionManagement.dao.enums.AccountStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {


    AccountOutput fromDbToOutput(Account accountdb);
    List<AccountOutput> fromDbToOutput(List<Account> accountdbs);

    @Mapping(target = "status", ignore = true)
    Account fromInputToDb(AccountInput accountInput);
    List<Account> fromInputToDb(List<AccountInput> accountInputs);
}
