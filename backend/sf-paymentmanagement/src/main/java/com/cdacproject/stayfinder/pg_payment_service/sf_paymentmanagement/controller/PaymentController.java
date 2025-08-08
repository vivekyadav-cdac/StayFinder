package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.controller;

import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.CreatePaymentRequest;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.PaymentResponse;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.RazorpayOrderRequest;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.RazorpayOrderResponse;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.service.PaymentService;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.service.RazorpayOrderService;
import com.razorpay.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final RazorpayOrderService razorpayOrderService;

    // Extract tenant ID from header and create payment
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestBody CreatePaymentRequest request,
            @RequestHeader("X-User-Id") Long tenantId
    ) {
        return ResponseEntity.ok(paymentService.createPayment(request, tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    // Get payments made by this tenant
    @GetMapping("/tenant")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByTenant(
            @RequestHeader("X-User-Id") Long tenantId
    ) {
        return ResponseEntity.ok(paymentService.getPaymentsByTenant(tenantId));
    }

    // Get payments received by this PG owner
    @GetMapping("/owner")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByOwner(
            @RequestHeader("X-User-Id") Long ownerId
    ) {
        return ResponseEntity.ok(paymentService.getPaymentsByOwner(ownerId));
    }

    @PostMapping("/create-order")
    public ResponseEntity<RazorpayOrderResponse> createOrder(@RequestBody RazorpayOrderRequest request) {
        try {
            BigDecimal amount = BigDecimal.valueOf(request.getAmount());
            String receiptId = "receipt_" + request.getBookingId();

            Order order = razorpayOrderService.createRazorpayOrder(amount, "INR", receiptId);

            RazorpayOrderResponse response = new RazorpayOrderResponse();
            response.setOrderId(order.get("id"));
            response.setKey("rzp_test_tgTBSwXi2pgDqY");
            response.setAmount(amount);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
