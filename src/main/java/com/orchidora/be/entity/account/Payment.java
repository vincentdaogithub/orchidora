package com.orchidora.be.entity.account;

import com.orchidora.be.entity.common.OrchidoraEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public final class Payment extends OrchidoraEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Account account;

    @Column(nullable = false, unique = true)
    private String paymentId;

    @Column(nullable = false)
    private String authenticationCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
