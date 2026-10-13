package com.rental.management.controller;

import com.rental.management.exception.NotFoundException;
import com.rental.management.model.Lease;
import com.rental.management.model.Property;
import com.rental.management.model.Tenant;
import com.rental.management.repository.LeaseRepository;
import com.rental.management.repository.PropertyRepository;
import com.rental.management.repository.TenantRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leases")
public class LeaseController extends CrudController<Lease> {

    private final PropertyRepository properties;
    private final TenantRepository tenants;

    public LeaseController(LeaseRepository repo, PropertyRepository properties, TenantRepository tenants) {
        super(repo);
        this.properties = properties;
        this.tenants = tenants;
    }

    /** Keeps the property's status in step with the lease: active lease = occupied. */
    @Override
    protected Lease save(Lease lease) {
        Property property = properties.findById(lease.getProperty().getId())
                .orElseThrow(() -> new NotFoundException("Property not found"));
        Tenant tenant = tenants.findById(lease.getTenant().getId())
                .orElseThrow(() -> new NotFoundException("Tenant not found"));
        lease.setProperty(property);
        lease.setTenant(tenant);
        property.setStatus(lease.isActive() ? Property.Status.OCCUPIED : Property.Status.AVAILABLE);
        properties.save(property);
        return super.save(lease);
    }

    @Override
    protected void afterDelete(Lease deleted) {
        Property property = deleted.getProperty();
        property.setStatus(Property.Status.AVAILABLE);
        properties.save(property);
    }
}
