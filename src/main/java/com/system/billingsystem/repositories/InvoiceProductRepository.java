package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.InvoiceProduct;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("InvoiceProductRepository")
public class InvoiceProductRepository implements BaseRepository<InvoiceProduct, UUID> {

    private final DSLContext dsl;

    @Autowired
    public InvoiceProductRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public InvoiceProduct save(InvoiceProduct persisted) {
        return null;
    }

    @Override
    public void saveAll(Iterable<InvoiceProduct> entities) {

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
    public List<InvoiceProduct> findAll() {
        return List.of();
    }

    @Override
    public Optional<InvoiceProduct> findById(UUID id) {
        return Optional.empty();
    }
}
