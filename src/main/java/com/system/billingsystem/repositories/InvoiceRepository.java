package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import domain.tables.records.CompanyRecord;
import domain.tables.records.InvoiceRecord;
import org.jetbrains.annotations.Nullable;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

import static domain.tables.Invoice.INVOICE;
import static domain.tables.Company.COMPANY;

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
                .set(INVOICE.INVOICE_VOUCHER, persisted.getInvoicevoucher().name())
                .set(INVOICE.INVOICED, persisted.isInvoiced())
                .set(INVOICE.PAID, persisted.isPaid())
                .set(INVOICE.TOTAL, persisted.getTotal())
                .set(INVOICE.CATEGORY, persisted.getCategory().name())
                .set(INVOICE.BUYER_COMPANY_ID, (persisted.getBuyerCompany() != null)? persisted.getBuyerCompany().getCompanyId(): null)
                .set(INVOICE.SELLER_COMPANY_ID, (persisted.getSellerCompany() != null)? persisted.getSellerCompany().getCompanyId(): null)
                .execute();

        return (execution == 1 ? id : null);
    }

    @Override
    public boolean update(Invoice persisted) {
        int execution =  dsl.update(INVOICE)
                .set(INVOICE.DATE, persisted.getDate().toLocalDateTime())
                .set(INVOICE.DISCOUNT, persisted.getDiscount())
                .set(INVOICE.INVOICE_VOUCHER, persisted.getInvoicevoucher().name())
                .set(INVOICE.INVOICED, persisted.isInvoiced())
                .set(INVOICE.PAID, persisted.isPaid())
                .set(INVOICE.TOTAL, persisted.getTotal())
                .set(INVOICE.CATEGORY, persisted.getCategory().name())
                .set(INVOICE.BUYER_COMPANY_ID, (persisted.getBuyerCompany() != null)? persisted.getBuyerCompany().getCompanyId(): null)
                .set(INVOICE.SELLER_COMPANY_ID, (persisted.getSellerCompany() != null)? persisted.getSellerCompany().getCompanyId(): null)
                .where(INVOICE.INVOICE_ID.eq(persisted.getInvoiceId()))
                .execute();

        return (execution == 1);
    }

    public Invoice findById(UUID id) {
        Result<?> result = dsl.select()
                .from(INVOICE)
                .innerJoin(COMPANY.as("seller")).on(INVOICE.SELLER_COMPANY_ID.eq(COMPANY.as("seller").COMPANY_ID))
                .innerJoin(COMPANY.as("buyer")).on(INVOICE.BUYER_COMPANY_ID.eq(COMPANY.as("buyer").COMPANY_ID))
                .where(INVOICE.INVOICE_ID.eq(id))
                .fetch();

        InvoiceRecord invoiceRecord = null;
        CompanyRecord companySellerRecord = null;
        CompanyRecord companyBuyerRecord = null;

        System.out.println(result);

        for (Record r : result) {
            invoiceRecord = r.into(INVOICE);
            companySellerRecord = r.into(COMPANY.as("seller"));
            companyBuyerRecord = r.into(COMPANY.as("buyer"));
        }

        System.out.println(invoiceRecord);
        System.out.println(companySellerRecord);
        System.out.println(companyBuyerRecord);

        if(invoiceRecord == null || companySellerRecord == null || companyBuyerRecord == null)
            return null;


        return new Invoice(
                invoiceRecord.getValue(INVOICE.INVOICE_ID),
            Timestamp.valueOf(invoiceRecord.getValue(INVOICE.DATE)),
                invoiceRecord.getValue(INVOICE.PAID),
                invoiceRecord.getValue(INVOICE.INVOICED),
                invoiceRecord.getValue(INVOICE.TOTAL),
                invoiceRecord.getValue(INVOICE.DISCOUNT),
            InvoiceVoucher.valueOf(invoiceRecord.getValue(INVOICE.INVOICE_VOUCHER)),
            InvoiceCategory.valueOf(invoiceRecord.getValue(INVOICE.CATEGORY)),
            new Company(
                    companySellerRecord.getValue(COMPANY.as("seller").COMPANY_ID),
                    companySellerRecord.getValue(COMPANY.as("seller").EMAIL),
                    companySellerRecord.getValue(COMPANY.as("seller").PHONE),
                    companySellerRecord.getValue(COMPANY.as("seller").NAME),
                    companySellerRecord.getValue(COMPANY.as("seller").ADDRESS),
                    companySellerRecord.getValue(COMPANY.as("seller").CUIT)

            ),
            new Company(
                    companyBuyerRecord.getValue(COMPANY.as("buyer").COMPANY_ID),
                    companyBuyerRecord.getValue(COMPANY.as("buyer").EMAIL),
                    companyBuyerRecord.getValue(COMPANY.as("buyer").PHONE),
                    companyBuyerRecord.getValue(COMPANY.as("buyer").NAME),
                    companyBuyerRecord.getValue(COMPANY.as("buyer").ADDRESS),
                    companyBuyerRecord.getValue(COMPANY.as("buyer").CUIT)

            )
        );
    }

    /*
    public Invoice findById(UUID id) {
        Record3<InvoiceRecord, CompanyRecord, CompanyRecord> record = (Record3<InvoiceRecord, CompanyRecord, CompanyRecord>) dsl
                .select(
                        INVOICE.field(1),
                        COMPANY.as("seller").field(2),
                        COMPANY.as("buyer").field(3)
                )
                .from(INVOICE)
                .innerJoin(COMPANY.as("seller")).on(INVOICE.SELLER_COMPANY_ID.eq(COMPANY.COMPANY_ID))
                .innerJoin(COMPANY.as("buyer")).on(INVOICE.BUYER_COMPANY_ID.eq(COMPANY.COMPANY_ID))
                .where(INVOICE.INVOICE_ID.eq(id))
                .fetchOne();

//        if (record == null);
            return null;


//        InvoiceRecord invoiceR = record.component1();
//        CompanyRecord companyS = record.component2();
//        CompanyRecord companyB = record.component3();
//
//        return new Invoice(
//            record.getValue(INVOICE.INVOICE_ID),
//            Timestamp.valueOf(record.getValue(INVOICE.DATE)),
//            record.getValue(INVOICE.PAID),
//            record.getValue(INVOICE.INVOICED),
//            record.getValue(INVOICE.TOTAL),
//            record.getValue(INVOICE.DISCOUNT),
//            InvoiceVoucher.valueOf(record.getValue(INVOICE.INVOICE_VOUCHER)),
//            InvoiceCategory.valueOf(record.getValue(INVOICE.CATEGORY)),
//            new Company(
//                    record.getValue(COMPANY.as("seller").COMPANY_ID),
//                    record.getValue(COMPANY.as("seller").EMAIL),
//                    record.getValue(COMPANY.as("seller").PHONE),
//                    record.getValue(COMPANY.as("seller").NAME),
//                    record.getValue(COMPANY.as("seller").ADDRESS),
//                    record.getValue(COMPANY.as("seller").CUIT)
//
//            ),
//            new Company(
//                    record.getValue(COMPANY.as("buyer").COMPANY_ID),
//                    record.getValue(COMPANY.as("buyer").EMAIL),
//                    record.getValue(COMPANY.as("buyer").PHONE),
//                    record.getValue(COMPANY.as("buyer").NAME),
//                    record.getValue(COMPANY.as("buyer").ADDRESS),
//                    record.getValue(COMPANY.as("buyer").CUIT)
//
//            )
//        );
    }
    */
    @Override
    protected Field<UUID> getIdField() {
        return INVOICE.INVOICE_ID;
    }
}
