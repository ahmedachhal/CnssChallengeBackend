package com.maltem.PaymentTransactionManagement.service.mappers;

import com.maltem.PaymentTransactionManagement.dao.dto.CustomerInput;
import com.maltem.PaymentTransactionManagement.dao.dto.CustomerOutput;
import com.maltem.PaymentTransactionManagement.dao.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerOutput fromDbToOutput(Customer customerdb);
    List<CustomerOutput> fromDbToOutput(List<Customer> customerdb);

    @Mapping(target = "status", ignore = true)
    Customer fromInputToDb(CustomerInput customerInput);
    List<Customer> fromInputToDb(List<CustomerInput> customerInput);
}
