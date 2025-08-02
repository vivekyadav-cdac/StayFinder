package com.pg.payment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pg.payment.dto.PaymentRequest;
import com.pg.payment.model.Booking;
import com.pg.payment.model.Payment;
import com.pg.payment.model.Room;
import com.pg.payment.model.Payment.PaymentStatusEnum;
import com.pg.payment.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    public List<Payment> getAllHistory() {
        return repo.findAll();
    }

    public Payment processPayment(PaymentRequest request, Integer tenantId) {
//        // Replace with actual booking service URL
//        String bookingUrl = "http://BOOKING-SERVICE/api/bookings/" + request.getBookingId();
//        Booking booking = restTemplate.getForObject(bookingUrl, Booking.class);
//
//        if (booking == null) {
//            throw new RuntimeException("Booking not found.");
//        }
//
//        if (!booking.getTenant_id().equals(tenantId)) {
//            throw new RuntimeException("Unauthorized: You cannot pay for another tenant's booking.");
//        }
//
//        Room room = booking.getRoom();
//        if (room == null) {
//            throw new RuntimeException("Room details missing for booking.");
//        }
    	 Booking booking = new Booking();
    	    booking.setBookingId(request.getBookingId());
    	    booking.setTenant_id(tenantId);

    	    Room room = new Room();
    	    room.setRent(5000.0);

    	    booking.setRoom(room);

        BigDecimal rent = BigDecimal.valueOf(room.getRent());

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(rent);
        payment.setMethod(request.getMethod());
        payment.setStatus(PaymentStatusEnum.COMPLETED);
        payment.setCreatedAt(LocalDateTime.now());

        return repo.save(payment);
    }

    public List<Payment> getHistoryByBookingId(Integer bookingId) {
        return repo.findByBookingId(bookingId);
    }

    public void deletePayment(Integer paymentId) {
        if (!repo.existsById(paymentId)) {
            throw new RuntimeException("Payment ID not found: " + paymentId);
        }
        repo.deleteById(paymentId);
    }

    public String createRazorpayOrder(BigDecimal amount) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("your_key", "your_secret");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount.multiply(BigDecimal.valueOf(100))); // in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_" + UUID.randomUUID());

        Order order = razorpay.orders.create(orderRequest);
        return order.toJson().toString();
    }

    public List<Payment> getPaymentsByTenant(Integer tenantId) {
        return repo.findByTenantId(tenantId);
    }

    public List<Payment> getPaymentsByOwner(Integer ownerId) {
        return repo.findByOwnerId(ownerId);
    }
}
