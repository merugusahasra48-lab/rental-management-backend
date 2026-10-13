package com.rental.management.controller;

import com.rental.management.model.Property;
import com.rental.management.repository.LeaseRepository;
import com.rental.management.repository.PaymentRepository;
import com.rental.management.repository.PropertyRepository;
import com.rental.management.repository.TenantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final PropertyRepository properties;
    private final TenantRepository tenants;
    private final LeaseRepository leases;
    private final PaymentRepository payments;

    public DashboardController(PropertyRepository properties, TenantRepository tenants,
                               LeaseRepository leases, PaymentRepository payments) {
        this.properties = properties;
        this.tenants = tenants;
        this.leases = leases;
        this.payments = payments;
    }

    @GetMapping
    public Map<String, Object> stats() {
        Map<String, Object> s = new LinkedHashMap<>();
        s.put("totalProperties", properties.count());
        s.put("occupied", properties.countByStatus(Property.Status.OCCUPIED));
        s.put("available", properties.countByStatus(Property.Status.AVAILABLE));
        s.put("maintenance", properties.countByStatus(Property.Status.MAINTENANCE));
        s.put("totalTenants", tenants.count());
        s.put("activeLeases", leases.countByActiveTrue());
        s.put("monthlyRentRoll", leases.activeRentRoll());
        s.put("totalCollected", payments.totalCollected());
        return s;
    }
}
