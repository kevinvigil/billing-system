package com.billingsystem.authservice.repository;

import com.billingsystem.authservice.entity.Customer;
import com.billingsystem.authservice.entity.microtypes.Id;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository("CustomerRepository")
@RequiredArgsConstructor
public class AuthRepository{

    private final DSLContext dsl;

//    public Id save(Customer persisted) {
//        UUID id = UUID.randomUUID();
//
//        int execution = dsl.insertInto(AUTH)
//                .set(AUTH.AUTH_ID, id)
//                .set(AUTH.EMAIL, persisted.getEmail().getValue())
////                .set(AUTH.USERNAME, CustomerNameMapper.toJson(persisted.getUsername()))
//                .set(AUTH.PASSWORD, persisted.getPassword())
//                .execute();
//
//        return (execution == 1 ? new CustomerId(id) : null);
//    }
//
//    public boolean update(Customer persisted) {
//        int execution = dsl.update(AUTH)
//                .set(AUTH.EMAIL, persisted.getEmail().getValue())
////                .set(AUTH.USERNAME, CustomerNameMapper.toJson(persisted.getUsername()))
//                .set(AUTH.PASSWORD, persisted.getPassword())
//                .where(AUTH.AUTH_ID.eq(persisted.getCustomerId().getValue()))
//                .execute();
//
//        return (execution == 1);
//    }
//
//    protected Field<UUID> getIdField() {
//        return AUTH.AUTH_ID;
//    }

}

