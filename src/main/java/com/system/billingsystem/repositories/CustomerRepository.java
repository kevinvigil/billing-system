package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Customer;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("CustomerRepository")
public class CustomerRepository implements BaseRepository<Customer, UUID> {

    private final DSLContext dsl;

    @Autowired
    public CustomerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Customer save(Customer persisted) {
        return null;
    }

    @Override
    public void saveAll(Iterable<Customer> entities) {

    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return Optional.empty();
    }
}
