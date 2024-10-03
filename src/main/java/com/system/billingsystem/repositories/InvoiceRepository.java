package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import com.system.billingsystem.entities.builders.companybuilder.CompanyBuilder;
import com.system.billingsystem.entities.builders.invoicebuilder.InvoiceBuilder;
import com.system.billingsystem.entities.microtypes.Cuit;
import com.system.billingsystem.entities.microtypes.Discount;
import com.system.billingsystem.entities.microtypes.Mail;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.microtypesmapper.AddressMapper;
import com.system.billingsystem.entities.microtypes.microtypesmapper.PhoneMapper;
import com.system.billingsystem.entities.microtypes.names.CompanyName;
import com.system.billingsystem.entities.Currency;
import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;
import domain.tables.records.CompanyRecord;
import domain.tables.records.InvoiceRecord;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
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
                .set(INVOICE.PRICE, persisted.getPrice().getValue())
                .set(INVOICE.CURRENCY, persisted.getPrice().getCurrency().name())
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
                .set(INVOICE.PRICE, persisted.getPrice().getValue())
                .set(INVOICE.CURRENCY, persisted.getPrice().getCurrency().name())
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
                .leftJoin(COMPANY.as("seller")).on(INVOICE.SELLER_COMPANY_ID.eq(COMPANY.as("seller").COMPANY_ID))
                .leftJoin(COMPANY.as("buyer")).on(INVOICE.BUYER_COMPANY_ID.eq(COMPANY.as("buyer").COMPANY_ID))
                .where(INVOICE.INVOICE_ID.eq(id.getValue()))
                .fetch();

        InvoiceRecord invoiceRecord = null;
        CompanyRecord companySellerRecord = null;
        CompanyRecord companyBuyerRecord = null;

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

        Company sellerCompany = companySellerRecord.getValue(COMPANY.COMPANY_ID) != null ?
                CompanyBuilder.newBuilder()
                        .companyId(new CompanyId(companySellerRecord.getValue(COMPANY.as("seller").COMPANY_ID)))
                        .name(new CompanyName(companySellerRecord.getValue(COMPANY.as("seller").NAME)))
                        .cuit(new Cuit(companySellerRecord.getValue(COMPANY.as("seller").CUIT)))
                        .address(AddressMapper.toDomain(companySellerRecord.getValue(COMPANY.as("seller").ADDRESS)))
                        .phone(PhoneMapper.toDomain(companySellerRecord.getValue(COMPANY.as("seller").PHONE)))
                        .email(new Mail(companySellerRecord.getValue(COMPANY.as("seller").EMAIL)))
                        .soldInvoices(null)
                        .purchasedInvoices(null)
                        .build() : null;

        Company buyerCompany = companyBuyerRecord.getValue(COMPANY.COMPANY_ID) != null ?
                CompanyBuilder.newBuilder()
                        .companyId(new CompanyId(companyBuyerRecord.getValue(COMPANY.as("buyer").COMPANY_ID)))
                        .name(new CompanyName(companyBuyerRecord.getValue(COMPANY.as("buyer").NAME)))
                        .cuit(new Cuit(companyBuyerRecord.getValue(COMPANY.as("buyer").CUIT)))
                        .address(AddressMapper.toDomain(companyBuyerRecord.getValue(COMPANY.as("buyer").ADDRESS)))
                        .phone(PhoneMapper.toDomain(companyBuyerRecord.getValue(COMPANY.as("buyer").PHONE)))
                        .email(new Mail(companyBuyerRecord.getValue(COMPANY.as("buyer").EMAIL)))
                        .soldInvoices(null)
                        .purchasedInvoices(null)
                        .build() : null;

        return InvoiceBuilder.newBuilder()
                .invoiceId(new InvoiceId(invoiceRecord.getValue(INVOICE.INVOICE_ID)))
                .date(Timestamp.valueOf(invoiceRecord.getValue(INVOICE.DATE)))
                .paid(invoiceRecord.getValue(INVOICE.PAID))
                .invoiced(invoiceRecord.getValue(INVOICE.INVOICED))
                .price(new InvoicePrice(Currency.valueOf(invoiceRecord.getValue(INVOICE.CURRENCY)) ,invoiceRecord.getValue(INVOICE.PRICE)))
                .discount(new Discount(invoiceRecord.getValue(INVOICE.DISCOUNT)))
                .invoiceVoucher(InvoiceVoucher.valueOf(invoiceRecord.getValue(INVOICE.INVOICE_VOUCHER)))
                .category(InvoiceCategory.valueOf(invoiceRecord.getValue(INVOICE.CATEGORY)))
                .sellerCompany(sellerCompany)
                .buyerCompany(buyerCompany).ListInvoiceProducts(null).build();
    }

    @Override
    protected Field<UUID> getIdField() {
        return INVOICE.INVOICE_ID;
    }

    public Optional<Invoice> getInvoiceStatus(UUID invoiceId) {
        return dsl.select(INVOICE.INVOICED, INVOICE.PAID)
                .from(INVOICE)
                .where(INVOICE.INVOICE_ID.eq(invoiceId))
                .fetchOptionalInto(Invoice.class);
    }
}
