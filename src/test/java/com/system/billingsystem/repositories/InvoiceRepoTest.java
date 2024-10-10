package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import com.system.billingsystem.entities.microtypes.Discount;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class InvoiceRepoTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    private static Invoice invoice;

    private InvoiceId baseId;
    
    @BeforeEach
    public void setUp(){
        invoice = Invoice.builder()
                .date(new Timestamp(1L))
                .invoiceVoucher(InvoiceVoucher.BILL)
                .invoiced(false)
                .paid(false)
                .discount(new Discount(0))
                .category(InvoiceCategory.A)
                .buyerCompany(null)
                .sellerCompany(null)
                .price(new InvoicePrice(BigDecimal.ONE)).build();

        baseId = invoiceRepository.save(invoice);
        assertNotNull(baseId);
        invoice.setInvoiceId(baseId);
    }

    @AfterEach
    void tearDown(){
        invoiceRepository.deleteById(baseId);
        Invoice proof = invoiceRepository.findById(baseId);
        assertNull(proof);
    }
    @Test
    public void testFindById(){
//        baseId = UUID.fromString("04c7d09a-0b61-4b79-bf44-f79271eaeeea");
        Invoice newInvoice = invoiceRepository.findById(baseId)  ;
        assertNotNull(newInvoice);
        assertEquals(baseId, newInvoice.getInvoiceId().getValue());
    }

    @Test
    public void testUpdateInvoice(){
        InvoicePrice aux = new InvoicePrice(BigDecimal.valueOf(0));
        invoice.setPrice(aux);
        invoiceRepository.update(invoice);
        Invoice newInvoice = invoiceRepository.findById(invoice.getInvoiceId())  ;
        assertNotNull(newInvoice);
        assertEquals(0 ,aux.compareTo(newInvoice.getPrice()));
    }

    @Test
    public void testFindAll(){
        Invoice newInvoice = Invoice.builder()
                .date(new Timestamp(2L))
                .invoiceVoucher(InvoiceVoucher.REFERENCE)
                .invoiced(true)
                .paid(true)
                .discount(new Discount(0))
                .category(InvoiceCategory.B)
                .buyerCompany(null)
                .sellerCompany(null)
                .price(new InvoicePrice(BigDecimal.valueOf(0))).build();

        assertNotNull(invoiceRepository.save(newInvoice));

        List<Invoice> invoices = invoiceRepository.findAll();
        assertNotNull(invoices);
        assertTrue(invoices.size()>1);
    }

    @Test
    public void testExistById(){
        assertTrue(invoiceRepository.existsById(baseId));
    }
}