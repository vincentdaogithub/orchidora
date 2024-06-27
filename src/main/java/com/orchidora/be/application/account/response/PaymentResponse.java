package com.orchidora.be.application.account.response;

import com.orchidora.be.entity.account.PaymentMethod;
import lombok.Builder;

import java.util.UUID;

@Builder
public final class PaymentResponse {

    private UUID id;
    private String paymentId;
    private PaymentMethod method;
}
