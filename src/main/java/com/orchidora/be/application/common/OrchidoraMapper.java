package com.orchidora.be.application.common;

public interface OrchidoraMapper<E, D> {

    D toDto(E e);
}
