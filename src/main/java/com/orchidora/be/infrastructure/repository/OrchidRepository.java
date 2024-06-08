package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.orchid.Orchid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrchidRepository extends JpaRepository<Orchid, UUID> {
}