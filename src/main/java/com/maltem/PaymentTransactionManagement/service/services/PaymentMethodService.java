package com.maltem.PaymentTransactionManagement.service.services;

import com.maltem.PaymentTransactionManagement.dao.dto.CustomerOutput;
import com.maltem.PaymentTransactionManagement.dao.dto.PaymentMethodInput;
import com.maltem.PaymentTransactionManagement.dao.dto.PaymentMethodOutput;
import com.maltem.PaymentTransactionManagement.dao.entities.PaymentMethod;
import com.maltem.PaymentTransactionManagement.dao.enums.PaymentStatus;
import com.maltem.PaymentTransactionManagement.dao.repository.PaymentMethodRepository;
import com.maltem.PaymentTransactionManagement.service.Exception.CustomerNotFoundException;
import com.maltem.PaymentTransactionManagement.service.mappers.PaymentMethodMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final CustomerService customerService;

    private final PaymentMethodMapper paymentMethodMapper;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, CustomerService customerService, PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.customerService = customerService;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    public  PaymentMethodOutput getPaymentMethod(String id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElse(null);
        if(paymentMethod == null ) return  null;
        PaymentMethodOutput result = paymentMethodMapper.fromDbToOutput(paymentMethod);
        return  result;
    }

    public  PaymentMethodOutput savePaymentMethod(PaymentMethodInput paymentMethodInput) {
        // check customer
        if(paymentMethodInput.getCustomer() != null){
            CustomerOutput customer = customerService.getCustomer(paymentMethodInput.getCustomer().getId());
            if(customer == null )  throw new CustomerNotFoundException("Customer not found with ID: " + paymentMethodInput.getCustomer().getId());
        }else{
             throw new CustomerNotFoundException("Unable to create a PaymentMethod with a null customer ");
        }

        PaymentMethod paymentMethod = paymentMethodMapper.fromInputToDb(paymentMethodInput);
        PaymentStatus paymentStatus = PaymentStatus.enumFromString(paymentMethodInput.getStatus());
        paymentMethod.setStatus(paymentStatus);
        PaymentMethod paymentMethodDb =  paymentMethodRepository.save(paymentMethod);
        PaymentMethodOutput result    = paymentMethodMapper.fromDbToOutput(paymentMethodDb);
        return result;
    }

    public  PaymentMethodOutput updatePaymentMethod(PaymentMethodInput paymentMethodInput, String id) {
        PaymentMethod newPaymentMethod = paymentMethodMapper.fromInputToDb(paymentMethodInput);
        PaymentMethod oldPaymentMethod = paymentMethodRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(newPaymentMethod,oldPaymentMethod,"id","createdDate");
        PaymentMethod paymentMethodNewDb = paymentMethodRepository.saveAndFlush(oldPaymentMethod);
        PaymentMethodOutput result = paymentMethodMapper.fromDbToOutput(paymentMethodNewDb);
        return result;
    }

    public  void deletePaymentMethod(String id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow();
        paymentMethod.setStatus(PaymentStatus.INACTIVE);
        paymentMethodRepository.save(paymentMethod);
    }
}
