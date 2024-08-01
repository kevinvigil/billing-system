package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Product;
import domain.tables.records.ProductRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static domain.tables.Product.PRODUCT;

@Repository("ProductRepository")
public class ProductRepository extends BaseRepository<ProductRecord,Product> {

    @Autowired
    protected ProductRepository(DSLContext dsl) {
        super(dsl, PRODUCT, Product.class);
    }

    @Override
    public Product save(Product persisted) {
        UUID id = UUID.randomUUID();

        return dsl.insertInto(PRODUCT)
                .set(PRODUCT.PRODUCT_ID, id)
                .set(PRODUCT.DESCRIPTION, persisted.getDescription())
                .set(PRODUCT.NAME, persisted.getName())
                .set(PRODUCT.PRICE, persisted.getPrice())
                .returning(PRODUCT).fetchOneInto(Product.class);
    }

    @Override
    protected Field<UUID> getIdField() {
        return PRODUCT.PRODUCT_ID;
    }

//    public Product deleteById(UUID uuid) {
//        return dsl.deleteFrom(PRODUCT)
//                .where(PRODUCT.PRODUCT_ID.eq(uuid))
//                .returning(PRODUCT).fetchOneInto(Product.class);
//    }
//
//    @Override
//    public void deleteAll() {
//        dsl.deleteFrom(PRODUCT).execute();
//    }
//
//    @Override
//    public boolean existsById(UUID uuid) {
//        return dsl.fetchExists(
//                dsl.select(PRODUCT.PRODUCT_ID)
//                        .from(PRODUCT)
//                        .where(PRODUCT.PRODUCT_ID.eq(uuid)));
//    }
//
//    @Override
//    public List<Product> findAll() {
//        return dsl.selectFrom(PRODUCT)
//                .fetchInto(Product.class);
//    }
//
//    @Override
//    public Product findById(UUID id) {
//        return dsl.selectFrom(PRODUCT)
//                .where(PRODUCT.PRODUCT_ID.eq(id))
//                .fetchOneInto(Product.class);
//    }
}
