package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.order.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, UUID> {
}