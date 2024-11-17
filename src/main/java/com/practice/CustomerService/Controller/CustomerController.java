package com.practice.CustomerService.Controller;

import com.practice.CustomerService.Entity.Customer;
import com.practice.CustomerService.NewCustomer;
import com.practice.CustomerService.Repo.CustomerRepo;
import com.practice.CustomerService.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PaymentService paymentService;

    @PostMapping(path = "/saveCustomer")
    ResponseEntity<Customer> saveCustomer(@RequestBody NewCustomer newCustomer) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(newCustomer, customer, "metaData");
        paymentService.addPayment(customer);
        return new ResponseEntity<Customer>(customerRepo.save(customer), HttpStatus.CREATED);
    }
}
