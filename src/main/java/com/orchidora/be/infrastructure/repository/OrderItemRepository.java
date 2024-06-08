package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}