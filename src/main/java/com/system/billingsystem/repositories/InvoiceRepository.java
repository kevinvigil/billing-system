package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import com.system.billingsystem.entities.microtypes.Cuit;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.microtypesmapper.AddressMapper;
import com.system.billingsystem.entities.microtypes.microtypesmapper.PhoneMapper;
import com.system.billingsystem.entities.microtypes.names.CompanyName;
import domain.tables.records.CompanyRecord;
import domain.tables.records.InvoiceRecord;
import org.jooq.*;
import org.jooq.Record;
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
    public InvoiceId save(Invoice persisted) {
        UUID id = UUID.randomUUID();
        int execution =  dsl.insertInto(INVOICE)
                .set(INVOICE.INVOICE_ID, id)
                .set(INVOICE.DATE, persisted.getDate().toLocalDateTime())
                .set(INVOICE.DISCOUNT, persisted.getDiscount().getDiscount())
                .set(INVOICE.INVOICE_VOUCHER, persisted.getInvoicevoucher().name())
                .set(INVOICE.INVOICED, persisted.isInvoiced())
                .set(INVOICE.PAID, persisted.isPaid())
                .set(INVOICE.TOTAL, persisted.getInvoicePrice().getValue())
                .set(INVOICE.CATEGORY, persisted.getCategory().name())
                .set(INVOICE.BUYER_COMPANY_ID, (persisted.getBuyerCompany() != null)? persisted.getBuyerCompany().getCompanyId().getValue(): null)
                .set(INVOICE.SELLER_COMPANY_ID, (persisted.getSellerCompany() != null)? persisted.getSellerCompany().getCompanyId().getValue(): null)
                .execute();

        return (execution == 1 ? new InvoiceId(id) : null);
    }

    @Override
    public boolean update(Invoice persisted) {
        int execution =  dsl.update(INVOICE)
                .set(INVOICE.DATE, persisted.getDate().toLocalDateTime())
                .set(INVOICE.DISCOUNT, persisted.getDiscount().getDiscount())
                .set(INVOICE.INVOICE_VOUCHER, persisted.getInvoicevoucher().name())
                .set(INVOICE.INVOICED, persisted.isInvoiced())
                .set(INVOICE.PAID, persisted.isPaid())
                .set(INVOICE.TOTAL, persisted.getInvoicePrice().getValue())
                .set(INVOICE.CATEGORY, persisted.getCategory().name())
                .set(INVOICE.BUYER_COMPANY_ID, (persisted.getBuyerCompany() != null)? persisted.getBuyerCompany().getCompanyId().getValue(): null)
                .set(INVOICE.SELLER_COMPANY_ID, (persisted.getSellerCompany() != null)? persisted.getSellerCompany().getCompanyId().getValue(): null)
                .where(INVOICE.INVOICE_ID.eq(persisted.getInvoiceId().getValue()))
                .execute();

        return (execution == 1);
    }

    public Invoice findById(InvoiceId id) {
        Result<?> result = dsl.select()
                .from(INVOICE)
                .innerJoin(COMPANY.as("seller")).on(INVOICE.SELLER_COMPANY_ID.eq(COMPANY.as("seller").COMPANY_ID))
                .innerJoin(COMPANY.as("buyer")).on(INVOICE.BUYER_COMPANY_ID.eq(COMPANY.as("buyer").COMPANY_ID))
                .where(INVOICE.INVOICE_ID.eq(id.getValue()))
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

        if(invoiceRecord == null)
            return null;


        return new Invoice(
                new InvoiceId(invoiceRecord.getValue(INVOICE.INVOICE_ID)),
            Timestamp.valueOf(invoiceRecord.getValue(INVOICE.DATE)),
                invoiceRecord.getValue(INVOICE.PAID),
                invoiceRecord.getValue(INVOICE.INVOICED),
                invoiceRecord.getValue(INVOICE.TOTAL),
                invoiceRecord.getValue(INVOICE.DISCOUNT),
            InvoiceVoucher.valueOf(invoiceRecord.getValue(INVOICE.INVOICE_VOUCHER)),
            InvoiceCategory.valueOf(invoiceRecord.getValue(INVOICE.CATEGORY)),
            new Company(
                    new CompanyId(companySellerRecord.getValue(COMPANY.as("seller").COMPANY_ID)),
                    companySellerRecord.getValue(COMPANY.as("seller").EMAIL),
                    PhoneMapper.toDomain(companySellerRecord.getValue(COMPANY.as("seller").PHONE)),
                    new CompanyName(companySellerRecord.getValue(COMPANY.as("seller").NAME)),
                    AddressMapper.toDomain(companyBuyerRecord.getValue(COMPANY.as("seller").ADDRESS)),
                    new Cuit(companySellerRecord.getValue(COMPANY.as("seller").CUIT))
            ),
            new Company(
                    new CompanyId(companyBuyerRecord.getValue(COMPANY.as("buyer").COMPANY_ID)),
                    companyBuyerRecord.getValue(COMPANY.as("buyer").EMAIL),
                    PhoneMapper.toDomain(companySellerRecord.getValue(COMPANY.as("buyer").PHONE)),
                    new CompanyName(companySellerRecord.getValue(COMPANY.as("buyer").NAME)),
                    AddressMapper.toDomain(companyBuyerRecord.getValue(COMPANY.as("buyer").ADDRESS)),
                    new Cuit(companySellerRecord.getValue(COMPANY.as("buyer").CUIT))

            )
        );
    }

    @Override
    protected Field<UUID> getIdField() {
        return INVOICE.INVOICE_ID;
    }
}
