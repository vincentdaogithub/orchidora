package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query(value = """
            SELECT a FROM Account a
            JOIN FETCH a.payments
            WHERE a.email = ?1
            """)
    Optional<Account> findByEmail(String email);
}