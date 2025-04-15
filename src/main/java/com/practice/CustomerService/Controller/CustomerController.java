package com.practice.CustomerService.Controller;

import com.practice.CustomerService.Entity.Customer;
import com.practice.CustomerService.NewCustomer;
import com.practice.CustomerService.Repo.CustomerRepo;
import com.practice.CustomerService.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PaymentService paymentService;

    @Value("${some.key:hi}")
    String message;

    @PostMapping(path = "/saveCustomer")
    ResponseEntity<Customer> saveCustomer(@RequestBody NewCustomer newCustomer) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(newCustomer, customer, "metaData");
        paymentService.addPayment(customer);
        return new ResponseEntity<Customer>(customerRepo.save(customer), HttpStatus.CREATED);
    }

    @GetMapping(path = "/message")
    ResponseEntity<String> getString() {
        log.info("Message {}", message);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
