package com.orchidora.be.infrastructure.repository;

import com.orchidora.be.entity.orchid.OrchidCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrchidCategoryRepository extends JpaRepository<OrchidCategory, UUID> {
}