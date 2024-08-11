package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;
import domain.tables.records.InvoiceProductRecord;
import domain.tables.records.ProductRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static domain.tables.InvoiceProduct.INVOICE_PRODUCT;
import static domain.tables.Product.PRODUCT;

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
                .set(INVOICE_PRODUCT.INVOICE_ID, (persisted.getInvoice() == null)? null:persisted.getInvoice().getInvoiceId())
                .set(INVOICE_PRODUCT.PRODUCT_ID, (persisted.getProduct() == null)? null:persisted.getProduct().getProductId())
                .set(INVOICE_PRODUCT.COUNT, persisted.getCount())
                .execute();

        return (execution == 1 ? id : null);
    }

    @Override
    public boolean update(InvoiceProduct persisted) {
        int execution = dsl.update(INVOICE_PRODUCT)
                .set(INVOICE_PRODUCT.INVOICE_ID, (persisted.getInvoice() == null)? null:persisted.getInvoice().getInvoiceId())
                .set(INVOICE_PRODUCT.PRODUCT_ID, (persisted.getProduct() == null)? null:persisted.getProduct().getProductId())
                .set(INVOICE_PRODUCT.COUNT, persisted.getCount())
                .where(INVOICE_PRODUCT.INVOICEPRODUCT_ID.eq(persisted.getInvoiceProductId()))
                .execute();

        return (execution == 1);
    }

    public List<InvoiceProduct> findByInvoiceId(UUID invoiceId){
        Result<?> result = dsl
                .select()
                .from(INVOICE_PRODUCT)
                .innerJoin(PRODUCT).on(INVOICE_PRODUCT.PRODUCT_ID.eq(PRODUCT.PRODUCT_ID))
                .where(INVOICE_PRODUCT.INVOICE_ID.eq(invoiceId))
                .fetch();

        System.out.println(result);

        List<InvoiceProduct> curr = new ArrayList<>();

        for(Record r: result){
            InvoiceProductRecord invoiceProductRecord = r.into(INVOICE_PRODUCT);
            ProductRecord productRecord = r.into(PRODUCT);

            curr.add(
                    new InvoiceProduct(
                            invoiceProductRecord.getValue(INVOICE_PRODUCT.INVOICEPRODUCT_ID),
                            invoiceProductRecord.getValue(INVOICE_PRODUCT.COUNT),
                            new Product(
                                    productRecord.getValue(PRODUCT.PRODUCT_ID),
                                    productRecord.getValue(PRODUCT.NAME),
                                    productRecord.getValue(PRODUCT.DESCRIPTION),
                                    productRecord.getValue(PRODUCT.PRICE)
                            )
                    )
            );
        }
        return curr;
    }

    @Override
    protected Field<UUID> getIdField() {
        return INVOICE_PRODUCT.INVOICEPRODUCT_ID;
    }
}
