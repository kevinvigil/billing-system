//package com.billingsystem.mainapp.repositories;
//
//import com.billingsystem.mainapp.entities.Customer;
//import com.billingsystem.mainapp.entities.microtypes.ids.CustomerId;
//import domain.tables.records.AuthRecord;
//import org.jooq.DSLContext;
//import org.jooq.Field;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.UUID;
//
//import static domain.tables.Auth.AUTH;
//
//@Repository("CustomerRepository")
//public class AuthRepository extends BaseRepository<AuthRecord, Customer> {
//
//    @Autowired
//    protected AuthRepository(DSLContext dsl) {
//        super(dsl, AUTH, Customer.class);
//    }
//
//    @Override
//    public CustomerId save(Customer persisted) {
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
//    @Override
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
//    @Override
//    protected Field<UUID> getIdField() {
//        return AUTH.AUTH_ID;
//    }
//
//}
