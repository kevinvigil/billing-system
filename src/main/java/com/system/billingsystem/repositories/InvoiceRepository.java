package com.system.billingSystem.repositories;

import com.system.billingSystem.entities.*;
import domain.tables.records.InvoiceRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static domain.tables.Invoice.INVOICE;

@Repository("InvoiceRepository")
public class InvoiceRepository extends BaseRepository<InvoiceRecord ,Invoice> {

    @Autowired
    protected InvoiceRepository(DSLContext dsl) {
        super(dsl, INVOICE, Invoice.class);
    }

    @Override
    public UUID save(Invoice persisted) {
        UUID id = UUID.randomUUID();
        int execution =  dsl.insertInto(INVOICE)
                .set(INVOICE.INVOICE_ID, id)
                .set(INVOICE.DATE, persisted.getDate().toLocalDateTime())
                .set(INVOICE.DISCOUNT, persisted.getDiscount())
                .set(INVOICE.INVOICEVOUCHER, persisted.getInvoicevoucher().name())
                .set(INVOICE.INVOICED, persisted.isInvoiced())
                .set(INVOICE.PAID, persisted.isPaid())
                .set(INVOICE.TOTAL, persisted.getTotal())
                .set(INVOICE.TYPE, persisted.getType().name())
                .set(INVOICE.BUYER_COMPANY_ID, (persisted.getBuyerCompany() != null)? persisted.getBuyerCompany().getCompany_id(): null)
                .set(INVOICE.SELLER_COMPANY_ID, (persisted.getSellerCompany() != null)? persisted.getSellerCompany().getCompany_id(): null)
                .execute();

        return (execution == 1 ? id : null);
    }

    @Override
    public boolean update(Invoice persisted) {
        int execution =  dsl.update(INVOICE)
                .set(INVOICE.DATE, persisted.getDate().toLocalDateTime())
                .set(INVOICE.DISCOUNT, persisted.getDiscount())
                .set(INVOICE.INVOICEVOUCHER, persisted.getInvoicevoucher().name())
                .set(INVOICE.INVOICED, persisted.isInvoiced())
                .set(INVOICE.PAID, persisted.isPaid())
                .set(INVOICE.TOTAL, persisted.getTotal())
                .set(INVOICE.TYPE, persisted.getType().name())
                .set(INVOICE.BUYER_COMPANY_ID, (persisted.getBuyerCompany() != null)? persisted.getBuyerCompany().getCompany_id(): null)
                .set(INVOICE.SELLER_COMPANY_ID, (persisted.getSellerCompany() != null)? persisted.getSellerCompany().getCompany_id(): null)
                .where(INVOICE.INVOICE_ID.eq(persisted.getInvoice_id()))
                .execute();

        return (execution == 1);
    }

    @Override
    protected Field<UUID> getIdField() {
        return INVOICE.INVOICE_ID;
    }
}
