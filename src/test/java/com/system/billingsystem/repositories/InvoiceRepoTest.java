package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
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
                .invoice_id(new UUID(1,1))
                .date(new Timestamp(1L))
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
        assertTrue(newInvoice.getInvoice_id().compareTo(new UUID(1,0)) > 0 );
    }

    @Test
    public void testFindById(){
        Invoice newInvoice = invoiceRepository.save(invoice);
        assertNotNull(newInvoice);
        Invoice newInvoice2 = invoiceRepository.findById(newInvoice.getInvoice_id())  ;
        assertNotNull(newInvoice2);
        assertEquals(newInvoice.getInvoice_id(), newInvoice2.getInvoice_id());
    }

    @Test
    public void testUpdateInvoice(){
        Invoice newInvoice = invoiceRepository.save(invoice);
        assertNotNull(newInvoice);

        newInvoice.setTotal(123456);
        invoiceRepository.save(newInvoice);
        Invoice newInvoice2 = invoiceRepository.findById(invoice.getInvoice_id())  ;
        assertNotNull(newInvoice2);
        assertEquals(123456, newInvoice2.getTotal());
    }

    @Test
    public void testDelete(){
        Invoice newInvoice = invoiceRepository.save(invoice);
        assertNotNull(newInvoice);
        invoiceRepository.deleteById(invoice.getInvoice_id());
        Invoice newInvoice2 = Optional.of(invoiceRepository.findById(invoice.getInvoice_id())).get()  ;
        assertNull(newInvoice2);
    }

    @Test
    public void testFindAll(){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Invoice newInvoice = Invoice.builder()
                .invoice_id(new UUID(2,2))
                .date(new Timestamp(2L))
                .invoiceVoucher(InvoiceVoucher.REFERENCE)
                .invoiced(true)
                .paid(true)
                .type(InvoiceType.B)
                .buyerCompany(null)
                .sellerCompany(null)
                .total(0).build();

        invoiceRepository.save(newInvoice);
        invoiceRepository.save(invoice);

        List<Invoice> invoices2 = invoiceRepository.findAll();
        assertNotNull(invoices2);
        assertEquals(2, invoices2.size());
    }
}