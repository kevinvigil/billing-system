package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import domain.tables.records.InvoiceRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static domain.tables.Invoice.INVOICE;

@Repository("InvoiceRepository")
public class InvoiceRepository extends BaseRepository<InvoiceRecord ,Invoice> {

    @Autowired
    protected InvoiceRepository(DSLContext dsl) {
        super(dsl, INVOICE, Invoice.class);
    }

    @Override
    public Invoice save(Invoice persisted) {
        UUID id = UUID.randomUUID();
        return dsl.insertInto(INVOICE)
                .set(INVOICE.INVOICE_ID, id)
                .set(INVOICE.DATE, persisted.getDate().toLocalDateTime())
                .set(INVOICE.DISCOUNT, persisted.getDiscount())
                .set(INVOICE.INVOICEVOUCHER, persisted.getInvoiceVoucher().name())
                .set(INVOICE.INVOICED, persisted.isInvoiced())
                .set(INVOICE.PAID, persisted.isPaid())
                .set(INVOICE.TOTAL, persisted.getTotal())
                .set(INVOICE.TYPE, persisted.getType().name())
                .set(INVOICE.BUYER_COMPANY_ID, (persisted.getBuyerCompany() != null)? persisted.getBuyerCompany().getCompany_id(): null)
                .set(INVOICE.SELLER_COMPANY_ID, (persisted.getSellerCompany() != null)? persisted.getSellerCompany().getCompany_id(): null)
                .returning()
                .fetchOneInto(Invoice.class);
    }

    @Override
    protected Field<UUID> getIdField() {
        return INVOICE.INVOICE_ID;
    }

//    public Invoice deleteById(UUID uuid) {
//        return dsl.deleteFrom(INVOICE)
//                .where(INVOICE.INVOICE_ID.eq(uuid))
//                .returning(INVOICE).fetchOneInto(Invoice.class);
//    }
//
//    @Override
//    public void deleteAll() {
//        dsl.deleteFrom(INVOICE).execute();
//    }
//
//    @Override
//    public boolean existsById(UUID uuid) {
//        return dsl.fetchExists(
//                dsl.select(INVOICE.INVOICE_ID)
//                        .from(INVOICE)
//                        .where(INVOICE.INVOICE_ID.eq(uuid)));
//    }
//
//    @Override
//    public List<Invoice> findAll() {
//        return dsl.selectFrom(INVOICE)
//                .fetchInto(Invoice.class);
//    }
//
//    @Override
//    public Invoice findById(UUID id) {
//        return dsl.selectFrom(INVOICE)
//                .where(INVOICE.INVOICE_ID.eq(id))
//                .fetchOneInto(Invoice.class);
//    }
}
