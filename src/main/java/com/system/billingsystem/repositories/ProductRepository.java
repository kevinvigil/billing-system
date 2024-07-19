package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Product;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("ProductRepository")
public class ProductRepository implements BaseRepository<Product, UUID> {

    private final DSLContext dsl;

    @Autowired
    public ProductRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Product save(Product persisted) {
        return null;
    }

    @Override
    public void saveAll(Iterable<Product> entities) {

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
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.empty();
    }
}
