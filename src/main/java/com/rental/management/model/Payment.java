package com.rental.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class Payment implements HasId {

    public enum Method { CASH, BANK_TRANSFER, UPI, CARD, CHEQUE }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Lease is required")
    @ManyToOne
    @JoinColumn(name = "lease_id")
    private Lease lease;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private Method method = Method.BANK_TRANSFER;

    private String note;
}
