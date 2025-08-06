package com.pg.payment.service;

import com.pg.payment.client.BookingClient;
import com.pg.payment.client.RoomClient;
import com.pg.payment.dto.BookingResponse;
import com.pg.payment.dto.PaymentRequest;
import com.pg.payment.dto.PaymentResponse;
import com.pg.payment.dto.RazorpayOrderRequest;
import com.pg.payment.dto.RazorpayOrderResponse;
import com.pg.payment.dto.RoomResponseDto;
import com.pg.payment.model.Payment;
import com.pg.payment.model.PaymentStatusEnum;
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

    public Payment processPayment(PaymentRequest request, Long tenantId){
    	
    	BookingResponse booking = bookingClient.getTenantBookings(request.getBookingId());
//   	BookingResponse booking = new BookingResponse();
    	
        if (!booking.getTenantId().equals(tenantId)) {
            throw new RuntimeException("Tenant ID mismatch");
        }
        
        RoomResponseDto room = roomClient.getRoom(booking.getRoomId());
//    	RoomResponseDto room = new RoomResponseDto();
        double rent = room.getRent();
//
        Payment payment = new Payment();
        payment.setBookingId(booking.getBookingId());
        payment.setAmount(rent);
        payment.setMethod(request.getMethod());
        payment.setStatus(com.pg.payment.model.PaymentStatusEnum.COMPLETED);
        payment.setCreatedAt(LocalDateTime.now());
       
        return repo.save(payment);
//        Long dummyBookingId = request.getBookingId(); // Use the one from request
//        Long dummyTenantId = tenantId;
//        Long dummyRoomId = 1001L; // Random room ID
//        double dummyRent = request.getAmount().doubleValue(); // Use the amount from frontend
//
//        // Proceed with payment creation using hardcoded/mock data
//        Payment payment = new Payment();
//        payment.setBookingId(dummyBookingId);
//        payment.setAmount(dummyRent);
//        payment.setMethod(request.getMethod());
//        payment.setStatus(PaymentStatusEnum.COMPLETED);
//        payment.setCreatedAt(LocalDateTime.now());
//
//        return repo.save(payment);
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
    

//    public List<Payment> getHistoryByBookingId(Integer bookingId) {
//        return repo.findByBookingId(bookingId);
//    }


    public List<PaymentResponse> getPaymentsByTenant(Long tenantId) {
        BookingResponse booking = bookingClient.getTenantBookings(tenantId);
        return repo.findByBookingId(booking.getBookingId());
    }
    
//    public List<Payment> getPaymentsByOwner(Long ownerId) {
//        
//    }

}
