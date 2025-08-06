package com.pg.payment.repository;

import com.pg.payment.dto.PaymentResponse;
import com.pg.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

 //   @Query("SELECT p FROM Payment p WHERE p.booking.bookingId = ?1")
     List<PaymentResponse> findByBookingId(Long bookingId);

//    @Query("SELECT p FROM Payment p WHERE p.booking.tenantId = ?1")
//    List<Payment> findByTenantId(Integer tenantId);
//
//    @Query("SELECT p FROM Payment p WHERE p.booking.room.pg.ownerId = ?1")
//    List<Payment> findByOwnerId(Integer ownerId);

}
