package com.maltem.PaymentTransactionManagement.web;

import com.maltem.PaymentTransactionManagement.dao.dto.CustomerInput;
import com.maltem.PaymentTransactionManagement.dao.dto.CustomerOutput;
import com.maltem.PaymentTransactionManagement.service.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/find/{id}")
    private CustomerOutput getCustomer(@PathVariable String id){
        return customerService.getCustomer(id);
    }

    @PostMapping("/save")
    private CustomerOutput saveCustomer(@RequestBody CustomerInput customerInput){
        return  customerService.saveCustomer(customerInput);
    }

    @PutMapping("/update/{id}")
    private CustomerOutput updateCustomer(@RequestBody CustomerInput customerInput, @PathVariable String id){
        return customerService.updateCustomer(customerInput, id);
    }

    @RequestMapping("/delete/{id}")
    private void deleteCostumer(@PathVariable String id){
         customerService.deleteCostumer(id);
    }
}
