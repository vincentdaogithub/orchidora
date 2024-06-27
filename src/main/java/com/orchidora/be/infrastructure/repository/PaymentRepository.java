package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.account.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}