package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("CompanyRepository")
public class CompanyRepository implements BaseRepository<Company, UUID> {

    private final DSLContext dsl;

    @Autowired
    public CompanyRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Company save(Company persisted) {
        return null;
    }

    @Override
    public void saveAll(Iterable<Company> entities) {

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
    public List<Company> findAll() {
        return List.of();
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return Optional.empty();
    }
}
