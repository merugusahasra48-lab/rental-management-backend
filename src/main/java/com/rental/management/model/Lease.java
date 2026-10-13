package com.rental.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class Lease implements HasId {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Property is required")
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @NotNull(message = "Tenant is required")
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "Monthly rent is required")
    private BigDecimal monthlyRent;

    private BigDecimal deposit;

    private boolean active = true;
}
