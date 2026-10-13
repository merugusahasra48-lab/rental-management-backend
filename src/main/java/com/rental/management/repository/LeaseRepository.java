package com.rental.management.repository;

import com.rental.management.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
    long countByActiveTrue();

    @Query("select coalesce(sum(l.monthlyRent), 0) from Lease l where l.active = true")
    BigDecimal activeRentRoll();
}
