package com.orchidora.be.application.account.mapper;

import com.orchidora.be.application.account.response.PaymentResponse;
import com.orchidora.be.application.common.OrchidoraMapper;
import com.orchidora.be.entity.account.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper implements OrchidoraMapper<Payment, PaymentResponse> {

    @Override
    public PaymentResponse toDto(Payment payment) {
        if (payment == null) {
            return null;
        }
        return PaymentResponse.builder()
                .id(payment.getId())
                .paymentId(payment.getPaymentId())
                .method(payment.getMethod())
                .build();
    }
}
