package com.system.billingSystem.repository;

import com.system.billingSystem.model.*;
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
                .id(1L)
                .date(offsetDateTime)
                .invoiceVoucher(InvoiceVoucher.BILL)
                .invoiced(false)
                .paid(false)
                .type(InvoiceType.A)
                .customer(null)
                .company(null)
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
        assertTrue(newInvoice.getId() > 0);
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
                .id(2L)
                .date(offsetDateTime)
                .invoiceVoucher(InvoiceVoucher.REFERENCE)
                .invoiced(true)
                .paid(true)
                .type(InvoiceType.B)
                .customer(null)
                .company(null)
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
        Invoice newInvoice = new Invoice(2L);
        Invoice newInvoice2 = new Invoice(3L);
        Invoice newInvoice3 = new Invoice(4L);

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