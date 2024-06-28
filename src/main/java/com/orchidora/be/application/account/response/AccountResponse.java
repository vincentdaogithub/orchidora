package com.orchidora.be.application.account.response;

import com.orchidora.be.entity.account.Role;
import com.orchidora.be.entity.common.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class AccountResponse {

    private UUID id;
    private String name;
    private String email;
    private Role role;
    private String phoneNumber;
    private String address;
    private ActiveStatus status;
    private List<PaymentResponse> payments;
}
