package com.orchidora.be.application.account.mapper;

import com.orchidora.be.application.account.response.AccountResponse;
import com.orchidora.be.application.account.response.PaymentResponse;
import com.orchidora.be.application.common.OrchidoraMapper;
import com.orchidora.be.entity.account.Account;
import com.orchidora.be.entity.account.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AccountMapper implements OrchidoraMapper<Account, AccountResponse> {

    private final OrchidoraMapper<Payment, PaymentResponse> paymentMapper;

    @Override
    public AccountResponse toDto(Account account) {
        if (account == null) {
            return null;
        }
        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .role(account.getRole())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .address(account.getAddress())
                .payments(account.getPayments().stream().map(paymentMapper::toDto).toList())
                .build();
    }
}
