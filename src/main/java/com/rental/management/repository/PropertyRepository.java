package com.rental.management.repository;

import com.rental.management.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    long countByStatus(Property.Status status);
}
