package com.maltem.PaymentTransactionManagement.service.services;

import com.maltem.PaymentTransactionManagement.dao.dto.CustomerInput;
import com.maltem.PaymentTransactionManagement.dao.dto.CustomerOutput;
import com.maltem.PaymentTransactionManagement.dao.entities.Customer;
import com.maltem.PaymentTransactionManagement.dao.enums.CustomerStatus;
import com.maltem.PaymentTransactionManagement.dao.repository.CustomerRepository;
import com.maltem.PaymentTransactionManagement.service.mappers.CustomerMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    public CustomerOutput getCustomer(String id) {
        Customer customerdb =  customerRepository.findById(id).orElse(null);
        if(customerdb == null) return  null;
        CustomerOutput result = customerMapper.fromDbToOutput(customerdb);
        return result;
    }

    public CustomerOutput saveCustomer(CustomerInput customerInput) {
        Customer customer = customerMapper.fromInputToDb(customerInput);
        CustomerStatus customerStatus = CustomerStatus.enumFromString(customerInput.getStatus());
        customer.setStatus(customerStatus);
        Customer customerdb = customerRepository.save(customer);
        CustomerOutput result = customerMapper.fromDbToOutput(customerdb);
        return result;
    }

    public CustomerOutput updateCustomer(CustomerInput customerInput, String id) {
        Customer newCustomer = customerMapper.fromInputToDb(customerInput);
        Customer oldCustomer = customerRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(newCustomer, oldCustomer, "id" , "createdDate");
        Customer customerNewDB = customerRepository.saveAndFlush(oldCustomer);
        CustomerOutput result = customerMapper.fromDbToOutput(customerNewDB);
        return result;
    }

    public void deleteCostumer(String id) {
        Customer customerdb = customerRepository.findById(id).orElse(null);
        customerdb.setStatus(CustomerStatus.INACTIVE);
        customerRepository.save(customerdb);
    }
}
