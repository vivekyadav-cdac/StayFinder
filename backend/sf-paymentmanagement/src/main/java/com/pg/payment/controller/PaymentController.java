package com.pg.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.payment.dto.PaymentRequest;
import com.pg.payment.model.Payment;
import com.pg.payment.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllHistory() {
    	List<Payment> history = paymentService.getAllHistory();
    	return ResponseEntity.ok(history);
    }
    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequest request) {
        Payment payment = paymentService.processPayment(request);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }
    
    @GetMapping
    @RequestMapping("/history/{bookingId}")
    public ResponseEntity<List<Payment>> getHistoryByBookingId(@PathVariable Integer bookingId){
    	List<Payment> payments = paymentService.getHistoryByBookingId(bookingId);
    	return ResponseEntity.ok(payments);
    }
    
    @DeleteMapping
    @RequestMapping("/delete/{paymentId}")
    public ResponseEntity<String> deletePayment(Integer paymentId) {
    	paymentService.deletePayment(paymentId);
    	return ResponseEntity.ok("Payment deleted successfully with ID: " + paymentId);
    }
}


