package com.orchidora.be.entity.order;

import com.orchidora.be.entity.common.OrchidoraEntity;
import com.orchidora.be.entity.orchid.Orchid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public final class OrderItem extends OrchidoraEntity {

    @ManyToOne(optional = false)
    private Order order;

    @ManyToOne
    private Orchid orchid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
