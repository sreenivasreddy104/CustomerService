package com.practice.CustomerService.Entity;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long customer_id;

    @Column
    String cName;

    @Column
    String cDept;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getId() {
        return customer_id;
    }

    public void setId(Long id) {
        this.customer_id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDept() {
        return cDept;
    }

    public void setcDept(String cDept) {
        this.cDept = cDept;
    }
}
