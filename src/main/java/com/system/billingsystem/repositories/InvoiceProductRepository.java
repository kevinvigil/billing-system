package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.InvoiceProduct;
import domain.tables.records.InvoiceProductRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static domain.tables.InvoiceProduct.INVOICE_PRODUCT;

@Repository("InvoiceProductRepository")
public class InvoiceProductRepository extends BaseRepository<InvoiceProductRecord,InvoiceProduct> {

    @Autowired
    protected InvoiceProductRepository(DSLContext dsl) {
        super(dsl, INVOICE_PRODUCT, InvoiceProduct.class);
    }

    @Override
    public UUID save(InvoiceProduct persisted) {
        UUID id = UUID.randomUUID();
        int execution =  dsl.insertInto(INVOICE_PRODUCT)
                .set(INVOICE_PRODUCT.INVOICEPRODUCT_ID, id)
                .set(INVOICE_PRODUCT.INVOICE_ID, (persisted.getInvoice() == null)? null:persisted.getInvoice().getInvoice_id())
                .set(INVOICE_PRODUCT.PRODUCT_ID, (persisted.getProduct() == null)? null:persisted.getProduct().getProduct_id())
                .set(INVOICE_PRODUCT.AMOUNT, persisted.getAmount())
                .execute();

        return (execution == 1 ? id : null);
    }

    @Override
    public boolean update(InvoiceProduct persisted) {
        int execution = dsl.update(INVOICE_PRODUCT)
                .set(INVOICE_PRODUCT.INVOICE_ID, (persisted.getInvoice() == null)? null:persisted.getInvoice().getInvoice_id())
                .set(INVOICE_PRODUCT.PRODUCT_ID, (persisted.getProduct() == null)? null:persisted.getProduct().getProduct_id())
                .set(INVOICE_PRODUCT.AMOUNT, persisted.getAmount())
                .where(INVOICE_PRODUCT.INVOICEPRODUCT_ID.eq(persisted.getInvoiceproductId()))
                .execute();

        return (execution == 1);
    }

    @Override
    protected Field<UUID> getIdField() {
        return INVOICE_PRODUCT.INVOICEPRODUCT_ID;
    }
}
