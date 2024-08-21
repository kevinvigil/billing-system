package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.entities.microtypes.microtypesmapper.CustomerNameMapper;
import domain.tables.records.CustomerRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.JSONB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static domain.tables.Customer.CUSTOMER;

@Repository("CustomerRepository")
public class CustomerRepository extends BaseRepository<CustomerRecord, Customer> {

    @Autowired
    protected CustomerRepository(DSLContext dsl) {
        super(dsl, CUSTOMER, Customer.class);
    }

    @Override
    public CustomerId save(Customer persisted) {
        UUID id = UUID.randomUUID();

        int execution = dsl.insertInto(CUSTOMER)
                .set(CUSTOMER.CUSTOMER_ID, id)
                .set(CUSTOMER.EMAIL, persisted.getEmail())
                .set(CUSTOMER.NAME, CustomerNameMapper.toJson(persisted.getName()))
                .set(CUSTOMER.PASSWORD, persisted.getPassword())
                .execute();

        return (execution == 1 ? new CustomerId(id) : null);
    }

    @Override
    public boolean update(Customer persisted) {
        int execution = dsl.update(CUSTOMER)
                .set(CUSTOMER.EMAIL, persisted.getEmail())
                .set(CUSTOMER.NAME, CustomerNameMapper.toJson(persisted.getName()))
                .set(CUSTOMER.PASSWORD, persisted.getPassword())
                .where(CUSTOMER.CUSTOMER_ID.eq(persisted.getCustomerId().getValue()))
                .execute();

        return (execution == 1);
    }

    @Override
    protected Field<UUID> getIdField() {
        return CUSTOMER.CUSTOMER_ID;
    }

}
