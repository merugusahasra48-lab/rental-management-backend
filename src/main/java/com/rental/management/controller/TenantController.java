package com.rental.management.controller;

import com.rental.management.model.Tenant;
import com.rental.management.repository.TenantRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenants")
public class TenantController extends CrudController<Tenant> {
    public TenantController(TenantRepository repo) {
        super(repo);
    }
}
