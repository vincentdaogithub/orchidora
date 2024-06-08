package com.orchidora.be.entity.inventory;

import com.orchidora.be.entity.common.OrchidoraEntity;
import com.orchidora.be.entity.orchid.Orchid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public final class OrchidInventory extends OrchidoraEntity {

    @OneToOne(optional = false)
    @JoinColumn(nullable = false, unique = true)
    private Orchid orchid;

    @Column(nullable = false)
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
