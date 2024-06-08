package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.account.AccountPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountPaymentRepository extends JpaRepository<AccountPayment, UUID> {
}