package com.practice.CustomerService.feignClient;

import com.practice.CustomerService.Entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "API-GATEWAY")
public interface PaymentFeignClient {

    @PostMapping("/api-gateway/payment-service/payments/addPayment")
    ResponseEntity<Payment> addPayment(@RequestBody Payment payment);
}
