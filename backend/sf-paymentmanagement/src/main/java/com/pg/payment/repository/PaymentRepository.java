package com.pg.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pg.payment.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	List<Payment> findByBookingId(Integer bookingId);

}
