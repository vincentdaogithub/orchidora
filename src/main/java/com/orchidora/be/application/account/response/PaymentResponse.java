package com.orchidora.be.application.account.response;

import com.orchidora.be.entity.account.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PaymentResponse {

    private UUID id;
    private String paymentId;
    private PaymentMethod method;
}
