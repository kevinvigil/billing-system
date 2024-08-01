package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

import static domain.tables.Company.COMPANY;

@Repository("CompanyRepository")
public class CompanyRepository implements BaseRepository<Company, UUID> {

    private final DSLContext dsl;

    @Autowired
    public CompanyRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Company save(Company persisted) {
        UUID id = UUID.randomUUID();

        return dsl.insertInto(COMPANY)
                .set(COMPANY.COMPANY_ID, id)
                .set(COMPANY.CUIT, persisted.getCuit())
                .set(COMPANY.DIRECTION, persisted.getDirection())
                .set(COMPANY.EMAIL, persisted.getEmail())
                .set(COMPANY.NAME, persisted.getName())
                .set(COMPANY.PHONE, persisted.getPhone())
                .returning(COMPANY).fetchOneInto(Company.class);
    }

    public Company deleteById(UUID uuid) {
        return dsl.deleteFrom(COMPANY)
                .where(COMPANY.COMPANY_ID.eq(uuid))
                .returning(COMPANY).fetchOneInto(Company.class);
    }

    @Override
    public void deleteAll() {
        dsl.deleteFrom(COMPANY).execute();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return dsl.fetchExists(
                dsl.select(COMPANY.COMPANY_ID)
                        .from(COMPANY)
                        .where(COMPANY.COMPANY_ID.eq(uuid)));
    }

    @Override
    public List<Company> findAll() {
        return dsl.selectFrom(COMPANY)
                .fetchInto(Company.class);
    }

    @Override
    public Company findById(UUID id) {
        return dsl.selectFrom(COMPANY)
                .where(COMPANY.COMPANY_ID.eq(id))
                .fetchOneInto(Company.class);
    }
}
