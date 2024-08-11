package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
import domain.tables.records.CompanyRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

import static domain.tables.Company.COMPANY;

@Repository("CompanyRepository")
public class CompanyRepository extends BaseRepository<CompanyRecord, Company> {

    @Autowired
    protected CompanyRepository(DSLContext dsl) {
        super(dsl, COMPANY, Company.class);
    }

    @Override
    public UUID save(Company persisted) {
        UUID id = UUID.randomUUID();
        int execution = dsl.insertInto(COMPANY)
                .set(COMPANY.COMPANY_ID, id)
                .set(COMPANY.CUIT, persisted.getCuit())
                .set(COMPANY.ADDRESS, persisted.getAddress())
                .set(COMPANY.EMAIL, persisted.getEmail())
                .set(COMPANY.NAME, persisted.getName())
                .set(COMPANY.PHONE, persisted.getPhone())
                .execute();

        return (execution == 1 ? id : null);
    }

    public boolean update(Company persisted){
        int execution = dsl.update(COMPANY)
                .set(COMPANY.CUIT, persisted.getCuit())
                .set(COMPANY.ADDRESS, persisted.getAddress())
                .set(COMPANY.EMAIL, persisted.getEmail())
                .set(COMPANY.NAME, persisted.getName())
                .set(COMPANY.PHONE, persisted.getPhone())
                .where(COMPANY.COMPANY_ID.eq(persisted.getCompanyId()))
                .execute();

        return (execution == 1);
    }

    @Override
    protected Field<UUID> getIdField() {
        return COMPANY.COMPANY_ID;
    }
}
