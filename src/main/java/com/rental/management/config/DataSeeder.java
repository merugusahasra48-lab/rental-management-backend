package com.rental.management.config;

import com.rental.management.model.*;
import com.rental.management.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Adds a little sample data the first time the app starts with an empty database. */
@Component
public class DataSeeder implements CommandLineRunner {

    private final PropertyRepository properties;
    private final TenantRepository tenants;
    private final LeaseRepository leases;
    private final PaymentRepository payments;

    public DataSeeder(PropertyRepository properties, TenantRepository tenants,
                      LeaseRepository leases, PaymentRepository payments) {
        this.properties = properties;
        this.tenants = tenants;
        this.leases = leases;
        this.payments = payments;
    }

    @Override
    public void run(String... args) {
        if (properties.count() > 0) return;

        Property p1 = property("Lakeview Residency 4B", "12 Lake Road", "Hyderabad", Property.Type.APARTMENT, 2, "24000", Property.Status.OCCUPIED);
        Property p2 = property("Green Park Villa", "7 Green Park Avenue", "Hyderabad", Property.Type.VILLA, 4, "55000", Property.Status.AVAILABLE);
        Property p3 = property("Market Street Shop 3", "21 Market Street", "Secunderabad", Property.Type.COMMERCIAL, null, "38000", Property.Status.MAINTENANCE);

        Tenant t1 = new Tenant();
        t1.setFullName("Anita Rao");
        t1.setEmail("anita.rao@example.com");
        t1.setPhone("9876500001");
        tenants.save(t1);

        Lease lease = new Lease();
        lease.setProperty(p1);
        lease.setTenant(t1);
        lease.setStartDate(LocalDate.now().minusMonths(3));
        lease.setEndDate(LocalDate.now().plusMonths(9));
        lease.setMonthlyRent(new BigDecimal("24000"));
        lease.setDeposit(new BigDecimal("72000"));
        lease.setActive(true);
        leases.save(lease);

        Payment pay = new Payment();
        pay.setLease(lease);
        pay.setAmount(new BigDecimal("24000"));
        pay.setPaymentDate(LocalDate.now().withDayOfMonth(1));
        pay.setMethod(Payment.Method.UPI);
        pay.setNote("Rent for this month");
        payments.save(pay);
    }

    private Property property(String name, String address, String city, Property.Type type,
                              Integer bedrooms, String rent, Property.Status status) {
        Property p = new Property();
        p.setName(name);
        p.setAddress(address);
        p.setCity(city);
        p.setType(type);
        p.setBedrooms(bedrooms);
        p.setMonthlyRent(new BigDecimal(rent));
        p.setStatus(status);
        return properties.save(p);
    }
}
