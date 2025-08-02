package com.pg.payment.repository;

import com.pg.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE p.booking.bookingId = ?1")
    List<Payment> findByBookingId(Integer bookingId);

    @Query("SELECT p FROM Payment p WHERE p.booking.tenant_id = ?1")
    List<Payment> findByTenantId(Integer tenantId);

    @Query("SELECT p FROM Payment p WHERE p.booking.room.pg_id = ?1")
    List<Payment> findByOwnerId(Integer ownerId);
}
