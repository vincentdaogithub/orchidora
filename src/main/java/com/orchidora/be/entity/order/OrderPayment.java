package com.orchidora.be.entity.order;

import com.orchidora.be.entity.account.AccountPayment;
import com.orchidora.be.entity.common.OrchidoraEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public final class OrderPayment extends OrchidoraEntity {

    @OneToOne(optional = false)
    private Order order;

    @ManyToOne(optional = false)
    private AccountPayment payment;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @CreationTimestamp
    private Instant paidOn;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
