package com.practice.CustomerService.service;

import com.practice.CustomerService.Entity.Customer;
import com.practice.CustomerService.Entity.Payment;
import com.practice.CustomerService.feignClient.PaymentFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    @Autowired
    PaymentFeignClient paymentFeignClient;

    @Transactional
    @CircuitBreaker(name = "paymentService",fallbackMethod = "handlePaymentServiceIfDown")
    public Customer addPayment(Customer customer) {
        Payment payment = new Payment();
        payment.setItemCost(120.0);
        payment.setItemName("Beryani");
        ResponseEntity<Payment> response = paymentFeignClient.addPayment(payment);
        Payment body = response.getBody();
        customer.setPayment(body);
        return customer;
    }

    Customer handlePaymentServiceIfDown(Customer customer, Throwable throwable) {
        customer.setPayment(null);
        return customer;
    }
}
