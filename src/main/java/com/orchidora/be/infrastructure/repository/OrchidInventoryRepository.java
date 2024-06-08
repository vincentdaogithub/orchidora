package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.inventory.OrchidInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrchidInventoryRepository extends JpaRepository<OrchidInventory, UUID> {
}