package com.maltem.PaymentTransactionManagement.web;

import com.maltem.PaymentTransactionManagement.dao.dto.PaymentMethodInput;
import com.maltem.PaymentTransactionManagement.dao.dto.PaymentMethodOutput;
import com.maltem.PaymentTransactionManagement.service.services.PaymentMethodService;
import com.maltem.PaymentTransactionManagement.service.services.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paymentMethod")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/find/{id}")
    private PaymentMethodOutput getPaymentMethod(@PathVariable String id){
        return paymentMethodService.getPaymentMethod(id);
    }

    @PostMapping("/save")
    private PaymentMethodOutput savePaymentMethod(@RequestBody PaymentMethodInput paymentMethodInput){
        return  paymentMethodService.savePaymentMethod(paymentMethodInput);
    }

    @PutMapping("/update/{id}")
    private PaymentMethodOutput updatePaymentMethod(@RequestBody PaymentMethodInput paymentMethodInput, @PathVariable String id){
        return paymentMethodService.updatePaymentMethod(paymentMethodInput, id);
    }

    @RequestMapping("/delete/{id}")
    private void deleteCostumer(@PathVariable String id){
         paymentMethodService.deletePaymentMethod(id);
    }
}
