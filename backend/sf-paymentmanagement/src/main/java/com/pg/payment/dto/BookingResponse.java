package com.pg.payment.dto;

import java.time.LocalDateTime;

import com.pg.payment.model.PaymentMethodEnum;
import com.pg.payment.model.PaymentStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Long bookingId;
    private Long pgId;
    private Long roomId;
    private Long tenantId;
    
}