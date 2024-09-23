package com.maltem.PaymentTransactionManagement.service.mappers;

import com.maltem.PaymentTransactionManagement.dao.dto.AccountInput;
import com.maltem.PaymentTransactionManagement.dao.dto.AccountOutput;
import com.maltem.PaymentTransactionManagement.dao.dto.PaymentMethodInput;
import com.maltem.PaymentTransactionManagement.dao.dto.PaymentMethodOutput;
import com.maltem.PaymentTransactionManagement.dao.entities.Account;
import com.maltem.PaymentTransactionManagement.dao.entities.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {


    PaymentMethodOutput fromDbToOutput(PaymentMethod paymentMethod);
    List<PaymentMethodOutput> fromDbToOutput(List<PaymentMethod> paymentMethods);

    @Mapping(target = "status", ignore = true)
    PaymentMethod fromInputToDb(PaymentMethodInput paymentMethodInput);
    List<PaymentMethod> fromInputToDb(List<PaymentMethodInput> paymentMethodInputs);
}
