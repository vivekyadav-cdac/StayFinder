package com.pg.payment.controller;

import com.pg.payment.dto.PaymentRequest;
import com.pg.payment.dto.PaymentResponse;
import com.pg.payment.dto.RazorpayOrderRequest;
import com.pg.payment.dto.RazorpayOrderResponse;
import com.pg.payment.model.Payment;

import com.pg.payment.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
//@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllHistory() {
        return ResponseEntity.ok(paymentService.getAllHistory());
    }

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequest request, HttpServletRequest httpRequest,
                                               @RequestHeader("X-Tenant-Id") Long tenantId) {
        System.out.println("tenant_id: " + tenantId);
        System.out.println("Received Request to make paymemt: " + request);
        return new ResponseEntity<>(paymentService.processPayment(request, tenantId), HttpStatus.CREATED);
    }

    @PostMapping("/razorpay/order")
    public ResponseEntity<RazorpayOrderResponse> createOrder(@RequestBody RazorpayOrderRequest request,
                                                            @RequestHeader("X-Tenant-Id") Integer tenant_id) throws Exception{
        System.out.println("tenant_id: " + tenant_id);
        System.out.println("Received Razorpay Order Request: " + request);

        return ResponseEntity.ok(paymentService.createRazorpayOrder(request));

    }

//    @GetMapping("/history/{booking_id}")
//    public ResponseEntity<List<Payment>> getHistoryByBookingId(@PathVariable Integer booking_id) {
//        return ResponseEntity.ok(paymentService.getHistoryByBookingId(booking_id));
//    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByTenant(@PathVariable Long tenantId) {
        return ResponseEntity.ok(paymentService.getPaymentsByTenant(tenantId));
    }

//    @GetMapping("/owner/{ownerId}")
//    public ResponseEntity<List<Payment>> getPaymentsByOwner(@PathVariable Integer owner_id) {
//        return ResponseEntity.ok(paymentService.getPaymentsByOwner(owner_id));
//    }
}
