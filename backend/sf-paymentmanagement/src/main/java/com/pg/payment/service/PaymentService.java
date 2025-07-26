package com.pg.payment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pg.payment.dto.PaymentRequest;
import com.pg.payment.model.Booking;
import com.pg.payment.model.Payment;
import com.pg.payment.model.Payment.PaymentStatusEnum;
import com.pg.payment.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo; 

    @Autowired
    private RestTemplate restTemplate;
    
    public List<Payment> getAllHistory() {
    	return repo.findAll();
    }

    public Payment processPayment(PaymentRequest request) {
    	//String url = "http://BOOKING-SERVICE/booking/" + request.getBookingId();
    	String url = "https://jsonplaceholder.typicode.com/posts/1";
    	
    	Booking booking = restTemplate.getForObject(url, Booking.class);
        
                Payment payment = new Payment();
                payment.setBookingId(request.getBookingId());
                payment.setAmount(request.getAmount());
                payment.setMethod(request.getMethod());
                payment.setStatus(PaymentStatusEnum.COMPLETED);
                payment.setCreatedAt(LocalDateTime.now());

                return repo.save(payment);
    }
    
    public List<Payment> getHistoryByBookingId(Integer bookingId){
    	return repo.findByBookingId(bookingId);
    }
    
    public void deletePayment(Integer paymentId) {
    	if(!repo.existsById(paymentId)) {
    		throw new RuntimeException("Payment ID not found: " + paymentId);
    	}
    	repo.deleteById(paymentId);
    }
}

