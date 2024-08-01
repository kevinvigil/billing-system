package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.InvoiceProduct;
import domain.tables.records.InvoiceProductRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static domain.tables.InvoiceProduct.INVOICE_PRODUCT;

@Repository("InvoiceProductRepository")
public class InvoiceProductRepository extends BaseRepository<InvoiceProductRecord,InvoiceProduct> {

    @Autowired
    protected InvoiceProductRepository(DSLContext dsl) {
        super(dsl, INVOICE_PRODUCT, InvoiceProduct.class);
    }

    @Override
    public InvoiceProduct save(InvoiceProduct persisted) {
        UUID id = UUID.randomUUID();
        return dsl.insertInto(INVOICE_PRODUCT)
                .set(INVOICE_PRODUCT.INVOICEPRODUCT_ID, id)
                .set(INVOICE_PRODUCT.INVOICE_ID, persisted.getInvoice().getInvoice_id())
                .set(INVOICE_PRODUCT.PRODUCT_ID, persisted.getProduct().getProduct_id())
                .set(INVOICE_PRODUCT.AMOUNT, persisted.getAmount())
                .returning().fetchOneInto(InvoiceProduct.class);
    }

    @Override
    protected Field<UUID> getIdField() {
        return INVOICE_PRODUCT.INVOICEPRODUCT_ID;
    }

//    @Override
//    public InvoiceProduct deleteById(UUID uuid) {
//        return dsl.deleteFrom(INVOICE_PRODUCT)
//                .where(INVOICE_PRODUCT.INVOICEPRODUCT_ID.eq(uuid))
//                .returning().fetchOneInto(InvoiceProduct.class);
//    }
//
//    @Override
//    public void deleteAll() {
//        dsl.deleteFrom(INVOICE_PRODUCT).execute();
//    }
//
//    @Override
//    public boolean existsById(UUID uuid) {
//        return dsl.fetchExists(dsl.selectFrom(INVOICE_PRODUCT)
//                .where(INVOICE_PRODUCT.INVOICEPRODUCT_ID.eq(uuid)));
//    }
//
//    @Override
//    public List<InvoiceProduct> findAll() {
//        return dsl.selectFrom(INVOICE_PRODUCT).fetchInto(InvoiceProduct.class);
//    }
//
//    @Override
//    public InvoiceProduct findById(UUID id) {
//        return dsl.selectFrom(INVOICE_PRODUCT)
//                .where(INVOICE_PRODUCT.INVOICEPRODUCT_ID.eq(id))
//                .fetchOneInto(InvoiceProduct.class);
//    }
}
