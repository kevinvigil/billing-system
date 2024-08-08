package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Product;
import domain.tables.records.ProductRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static domain.tables.Product.PRODUCT;

@Repository("ProductRepository")
public class ProductRepository extends BaseRepository<ProductRecord, Product> {

    @Autowired
    protected ProductRepository(DSLContext dsl) {
        super(dsl, PRODUCT, Product.class);
    }

    @Override
    public UUID save(Product persisted) {
        UUID id = UUID.randomUUID();
        int execution = dsl.insertInto(PRODUCT)
                .set(PRODUCT.PRODUCT_ID, id)
                .set(PRODUCT.DESCRIPTION, persisted.getDescription())
                .set(PRODUCT.NAME, persisted.getName())
                .set(PRODUCT.PRICE, persisted.getPrice())
                .execute();

        return (execution == 1 ? id : null);
    }

    @Override
    public boolean update(Product persisted) {
        int execution = dsl.update(PRODUCT)
                .set(PRODUCT.DESCRIPTION, persisted.getDescription())
                .set(PRODUCT.NAME, persisted.getName())
                .set(PRODUCT.PRICE, persisted.getPrice())
                .where(PRODUCT.PRODUCT_ID.eq(persisted.getProductId()))
                .execute();

        return (execution == 1);
    }

    @Override
    protected Field<UUID> getIdField() {
        return PRODUCT.PRODUCT_ID ;
    }
}
