package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("InvoiceRepository")
public class InvoiceRepository implements BaseRepository<Invoice, UUID> {

    private final DSLContext dsl;

    @Autowired
    public InvoiceRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Invoice save(Invoice persisted) {
        return null;
    }

    @Override
    public void saveAll(Iterable<Invoice> entities) {

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
    public List<Invoice> findAll() {
        return List.of();
    }

    @Override
    public Optional<Invoice> findById(UUID id) {
        return Optional.empty();
    }
}
