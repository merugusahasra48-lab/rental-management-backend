package com.rental.management.controller;

import com.rental.management.exception.NotFoundException;
import com.rental.management.model.HasId;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Shared list / get / create / update / delete endpoints. Subclasses only add their URL and any rules. */
public abstract class CrudController<T extends HasId> {

    protected final JpaRepository<T, Long> repo;

    protected CrudController(JpaRepository<T, Long> repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<T> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public T one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Record " + id + " was not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@Valid @RequestBody T body) {
        body.setId(null);
        return save(body);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable Long id, @Valid @RequestBody T body) {
        one(id);
        body.setId(id);
        return save(body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        T existing = one(id);
        repo.delete(existing);
        repo.flush();
        afterDelete(existing);
    }

    /** Hook: runs for both create and update. */
    protected T save(T entity) {
        return repo.save(entity);
    }

    /** Hook: runs after a successful delete. */
    protected void afterDelete(T deleted) {
    }
}
