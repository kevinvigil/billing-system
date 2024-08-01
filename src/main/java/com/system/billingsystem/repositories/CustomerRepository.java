package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Customer;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static domain.tables.Customer.CUSTOMER;

@Repository("CustomerRepository")
public class CustomerRepository implements BaseRepository<Customer, UUID> {

    private final DSLContext dsl;

    @Autowired
    public CustomerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Customer save(Customer persisted) {
        UUID id = UUID.randomUUID();
        return dsl.insertInto(CUSTOMER)
                .set(CUSTOMER.CUSTOMER_ID, id)
                .set(CUSTOMER.EMAIL, persisted.getEmail())
                .set(CUSTOMER.NAME, persisted.getName())
                .set(CUSTOMER.PASSWORD, persisted.getPassword())
                .returning().fetchOneInto(Customer.class);
    }

    @Override
    public Customer deleteById(UUID uuid) {
        return dsl.deleteFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_ID.eq(uuid))
                .returning().fetchOneInto(Customer.class);
    }

    @Override
    public void deleteAll() {
        dsl.deleteFrom(CUSTOMER).execute();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return dsl.fetchExists(dsl.select(CUSTOMER.CUSTOMER_ID)
                .from(CUSTOMER).where(CUSTOMER.CUSTOMER_ID.eq(uuid)));
    }

    @Override
    public List<Customer> findAll() {
        return dsl.selectFrom(CUSTOMER)
                .fetchInto(Customer.class);
    }

    @Override
    public Customer findById(UUID id) {
        return dsl.selectFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_ID.eq(id))
                .fetchOneInto(Customer.class);
    }
}
