package com.pg.payment.controller;

import com.pg.payment.dto.PaymentRequest;
import com.pg.payment.model.Payment;

import com.pg.payment.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllHistory() {
        return ResponseEntity.ok(paymentService.getAllHistory());
    }

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequest request, HttpServletRequest httpRequest) {
    	Integer tenantId = 200;
        return new ResponseEntity<>(paymentService.processPayment(request, tenantId), HttpStatus.CREATED);
    }

    @GetMapping("/history/{bookingId}")
    public ResponseEntity<List<Payment>> getHistoryByBookingId(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(paymentService.getHistoryByBookingId(bookingId));
    }

//    @DeleteMapping("/delete/{paymentId}")
//    public ResponseEntity<String> deletePayment(@PathVariable Integer paymentId) {
//        paymentService.deletePayment(paymentId);
//        return ResponseEntity.ok("Payment deleted successfully with ID: " + paymentId);
//    }

    @PostMapping("/razorpay/webhook")
    public ResponseEntity<String> razorpayWebhook(@RequestBody String response) {
        // TODO: Validate Razorpay signature and update payment status
        return ResponseEntity.ok("Webhook received and processed.");
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<Payment>> getPaymentsByTenant(@PathVariable Integer tenantId) {
        return ResponseEntity.ok(paymentService.getPaymentsByTenant(tenantId));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Payment>> getPaymentsByOwner(@PathVariable Integer ownerId) {
        return ResponseEntity.ok(paymentService.getPaymentsByOwner(ownerId));
    }
}
