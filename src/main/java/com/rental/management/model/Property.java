package com.rental.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
public class Property implements HasId {

    public enum Type { APARTMENT, HOUSE, VILLA, COMMERCIAL }
    public enum Status { AVAILABLE, OCCUPIED, MAINTENANCE }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    private String city;

    @Enumerated(EnumType.STRING)
    private Type type = Type.APARTMENT;

    private Integer bedrooms;

    @NotNull(message = "Monthly rent is required")
    @DecimalMin(value = "0.0", message = "Monthly rent cannot be negative")
    private BigDecimal monthlyRent;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;
}
