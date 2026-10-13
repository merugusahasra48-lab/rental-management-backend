package com.rental.management.controller;

import com.rental.management.model.Property;
import com.rental.management.repository.PropertyRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/properties")
public class PropertyController extends CrudController<Property> {
    public PropertyController(PropertyRepository repo) {
        super(repo);
    }
}
