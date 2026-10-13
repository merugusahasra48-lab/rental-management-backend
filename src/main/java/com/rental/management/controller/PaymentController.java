package com.rental.management.controller;

import com.rental.management.model.Payment;
import com.rental.management.repository.PaymentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController extends CrudController<Payment> {
    public PaymentController(PaymentRepository repo) {
        super(repo);
    }
}
