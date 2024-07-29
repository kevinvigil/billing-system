package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static domain.tables.Company.COMPANY;

@Repository("CompanyRepository")
public class CompanyRepository implements BaseRepository<Company, UUID> {

    private DSLContext dsl;

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

        return dsl.select(COMPANY.COMPANY_ID, COMPANY.NAME)
                .from(COMPANY)
                .fetchInto(Company.class);
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return Optional.empty();
    }
}
