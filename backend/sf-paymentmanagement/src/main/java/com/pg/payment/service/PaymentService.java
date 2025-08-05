package com.pg.payment.service;

import com.pg.payment.client.BookingClient;
import com.pg.payment.client.RoomClient;
import com.pg.payment.dto.PaymentRequest;
import com.pg.payment.dto.RazorpayOrderRequest;
import com.pg.payment.dto.RazorpayOrderResponse;
import com.pg.payment.model.Booking;
import com.pg.payment.model.Payment;
import com.pg.payment.model.Room;
import com.pg.payment.model.Payment.PaymentStatusEnum;
import com.pg.payment.repository.PaymentRepository;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo;

    @Autowired
    private BookingClient bookingClient;
    
    @Autowired
    private RoomClient roomClient;

    public List<Payment> getAllHistory() {
        return repo.findAll();
    }

    public Payment processPayment(PaymentRequest request, Integer tenant_id) {

   
        Booking booking = new Booking();
        booking.setBookingId(request.getBookingId());
        booking.setTenant_id(tenant_id);

        Room room = new Room();
        room.setRent(request.getAmount());

        booking.setRoom(room);

        BigDecimal rent = room.getRent();

//        if (booking == null) {
//            throw new RuntimeException("Booking not found.");
//        }
//
//        // Validate tenant access
//        if (!booking.getTenant_id().equals(tenant_id)) {
//            throw new RuntimeException("Unauthorized: You cannot pay for another tenant's booking.");
        //       }
//
        // Save payment
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(rent);
        payment.setMethod(request.getMethod());
        payment.setStatus(PaymentStatusEnum.COMPLETED);
        payment.setCreatedAt(LocalDateTime.now());

        return repo.save(payment);
    }

    public RazorpayOrderResponse createRazorpayOrder(RazorpayOrderRequest request) throws RazorpayException{
        RazorpayClient razorpay = new RazorpayClient("rzp_test_tgTBSwXi2pgDqY", "smpSmWEYt3eH72Doc3B10OXK");

        JSONObject options = new JSONObject();
        int amountInPaise = request.getAmount().multiply(BigDecimal.valueOf(100)).intValue();
        options.put("amount", amountInPaise);
        options.put("currency", "INR");
        options.put("receipt", "txn_" + UUID.randomUUID());
        options.put("payment_capture", 1);

        Order order = razorpay.orders.create(options);

        System.out.println("order.get: " + order.get("id"));
        return RazorpayOrderResponse.builder()
                .orderId(order.get("id"))
                .key("rzp_test_tgTBSwXi2pgDqY")
                .amount(request.getAmount())
                .build();
    }

    public List<Payment> getHistoryByBookingId(Integer bookingId) {
        return repo.findByBookingId(bookingId);
    }


    public List<Payment> getPaymentsByTenant(Integer tenantId) {
        return repo.findByTenantId(tenantId);
    }

    public List<Payment> getPaymentsByOwner(Integer ownerId) {
        return repo.findByOwnerId(ownerId);
    }
}
