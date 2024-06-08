package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}