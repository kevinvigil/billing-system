package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class InvoiceRepoTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    private static Invoice invoice;

    @BeforeAll
    public static void setUp(){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        invoice = Invoice.builder()
                .id(new UUID(1,1))
                .date(offsetDateTime)
                .invoiceVoucher(InvoiceVoucher.BILL)
                .invoiced(false)
                .paid(false)
                .type(InvoiceType.A)
                .buyerCompany(null)
                .sellerCompany(null)
                .total(0).build();
    }

    @AfterEach
    void tearDown(){
        invoiceRepository.deleteAll();
    }

    @Test
    public void testCreateInvoice(){
        Invoice newInvoice = invoiceRepository.save(invoice);
        assertNotNull(newInvoice);
        assertTrue(newInvoice.getId().compareTo(new UUID(1,0)) > 0 );
    }

    @Test
    public void testFindById(){
        Invoice newInvoice = invoiceRepository.save(invoice);
        assertNotNull(newInvoice);
        Invoice newInvoice2 = invoiceRepository.findById(newInvoice.getId()).orElse(null);
        assertNotNull(newInvoice2);
        assertEquals(newInvoice.getId(), newInvoice2.getId());
    }

    @Test
    public void testUpdateInvoice(){
        Invoice newInvoice = invoiceRepository.save(invoice);
        assertNotNull(newInvoice);

        newInvoice.setTotal(123456);
        invoiceRepository.save(newInvoice);
        Invoice newInvoice2 = invoiceRepository.findById(invoice.getId()).orElse(null);
        assertNotNull(newInvoice2);
        assertEquals(123456, newInvoice2.getTotal());
    }

    @Test
    public void testDelete(){
        Invoice newInvoice = invoiceRepository.save(invoice);
        assertNotNull(newInvoice);
        invoiceRepository.deleteById(invoice.getId());
        Invoice newInvoice2 = Optional.of(invoiceRepository.findById(invoice.getId())).get().orElse(null);
        assertNull(newInvoice2);
    }

    @Test
    public void testFindAll(){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Invoice newInvoice = Invoice.builder()
                .id(new UUID(2,2))
                .date(offsetDateTime)
                .invoiceVoucher(InvoiceVoucher.REFERENCE)
                .invoiced(true)
                .paid(true)
                .type(InvoiceType.B)
                .buyerCompany(null)
                .sellerCompany(null)
                .total(0).build();
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(newInvoice);
        invoices.add(invoice);

        invoiceRepository.saveAll(invoices);

        List<Invoice> invoices2 = invoiceRepository.findAll();
        assertNotNull(invoices2);
        assertEquals(2, invoices2.size());
    }

    @Test
    public void testSaveAll(){
        Invoice newInvoice = new Invoice(new UUID(2,2));
        Invoice newInvoice2 = new Invoice(new UUID(3,3));
        Invoice newInvoice3 = new Invoice(new UUID(4,4));

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);
        invoices.add(newInvoice);
        invoices.add(newInvoice2);
        invoices.add(newInvoice3);

        invoiceRepository.saveAll(invoices);

        List<Invoice> newInvoices = invoiceRepository.findAll();
        assertNotNull(newInvoices);
        assertEquals(invoices.size(), newInvoices.size());
    }
}