package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.builders.invoiceproductbuilder.InvoiceProductBuilder;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.ids.InvoiceProductId;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;
import domain.tables.records.InvoiceProductRecord;
import domain.tables.records.ProductRecord;
import org.jetbrains.annotations.NotNull;
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
    public InvoiceProductId save(InvoiceProduct persisted) {
        UUID id = UUID.randomUUID();
        int execution =  dsl.insertInto(INVOICE_PRODUCT)
                .set(INVOICE_PRODUCT.INVOICE_ID, (persisted.getInvoice() == null)? null:persisted.getInvoice().getInvoiceId().getValue())
                .set(INVOICE_PRODUCT.PRODUCT_ID, (persisted.getProduct() == null)? null:persisted.getProduct().getProductId().getValue())
                .set(INVOICE_PRODUCT.COUNT, persisted.getCount())
                .execute();

        return (execution == 1 ? new InvoiceProductId(id) : null);
    }

    @Override
    public boolean update(InvoiceProduct persisted) {
        int execution = dsl.update(INVOICE_PRODUCT)
                .set(INVOICE_PRODUCT.INVOICE_ID, (persisted.getInvoice() == null)? null:persisted.getInvoice().getInvoiceId().getValue())
                .set(INVOICE_PRODUCT.PRODUCT_ID, (persisted.getProduct() == null)? null:persisted.getProduct().getProductId().getValue())
                .set(INVOICE_PRODUCT.COUNT, persisted.getCount())
                .where(INVOICE_PRODUCT.INVOICE_ID.eq(persisted.getInvoice().getInvoiceId().getValue()),
                        INVOICE_PRODUCT.PRODUCT_ID.eq(persisted.getProduct().getProductId().getValue()))
                .execute();

        return (execution == 1);
    }

    public InvoiceProduct delete(InvoiceId invoiceId, ProductId productId){
        return dsl.deleteFrom(INVOICE_PRODUCT)
                .where(INVOICE_PRODUCT.INVOICE_ID.eq(invoiceId.getValue()),
                        INVOICE_PRODUCT.PRODUCT_ID.eq(productId.getValue()))
                .returning().fetchOneInto(InvoiceProduct.class);
    }

    public boolean exists(InvoiceId invoiceId, ProductId productId){
        return dsl.fetchExists(
                dsl.selectFrom(INVOICE_PRODUCT)
                        .where(INVOICE_PRODUCT.INVOICE_ID.eq(invoiceId.getValue()),
                                INVOICE_PRODUCT.PRODUCT_ID.eq(productId.getValue()))
        );
    }

    public List<InvoiceProduct> findAll(){
        return dsl.selectFrom(INVOICE_PRODUCT).fetchInto(InvoiceProduct.class);
    }

    public InvoiceProduct findById(InvoiceId invoiceId, ProductId productId) {
        return dsl.selectFrom(INVOICE_PRODUCT)
                .where(INVOICE_PRODUCT.INVOICE_ID.eq(invoiceId.getValue()),
                        INVOICE_PRODUCT.PRODUCT_ID.eq(productId.getValue()))
                .fetchOneInto(InvoiceProduct.class);
    }

    public List<InvoiceProduct> findByInvoiceId(InvoiceId invoiceId){
        Result<?> result = dsl
                .select()
                .from(INVOICE_PRODUCT)
                .innerJoin(PRODUCT).on(INVOICE_PRODUCT.PRODUCT_ID.eq(PRODUCT.PRODUCT_ID))
                .where(INVOICE_PRODUCT.INVOICE_ID.eq(invoiceId.getValue()))
                .fetch();

        return getInvoiceProductsWhitId(result);
    }

    public List<InvoiceProduct> findByProductId(ProductId productId){
        Result<?> result = dsl
                .select()
                .from(INVOICE_PRODUCT)
                .innerJoin(PRODUCT).on(INVOICE_PRODUCT.PRODUCT_ID.eq(PRODUCT.PRODUCT_ID))
                .where(INVOICE_PRODUCT.PRODUCT_ID.eq(productId.getValue()))
                .fetch();

        return getInvoiceProductsWhitId(result);
    }

    @NotNull
    private List<InvoiceProduct> getInvoiceProductsWhitId(Result<?> result) {
        List<InvoiceProduct> curr = new ArrayList<>();

        for(Record r: result){
            InvoiceProductRecord invoiceProductRecord = r.into(INVOICE_PRODUCT);
            ProductRecord productRecord = r.into(PRODUCT);

            curr.add(
                    InvoiceProductBuilder.newBuilder()
                            .count(invoiceProductRecord.getValue(INVOICE_PRODUCT.COUNT))
                            .invoice(null)
                            .product(
                                    new Product(
                                            new ProductId(productRecord.getValue(PRODUCT.PRODUCT_ID)),
                                            new ProductName(productRecord.getValue(PRODUCT.NAME)),
                                            productRecord.getValue(PRODUCT.DESCRIPTION),
                                            new ProductPrice(productRecord.getValue(PRODUCT.PRICE))

                                    )
                            ).build()
            );
        }
        return curr;
    }

    @Override
    protected Field<UUID> getIdField() {
        return null;
    }
}
