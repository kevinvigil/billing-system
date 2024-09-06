package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.microtypes.ids.InvoiceProductId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class InvoiceProductRepoTest {

    private final InvoiceProductRepository invoiceProductRepo;

    private static InvoiceProduct invoiceProduct;

    private InvoiceProductId baseId;

    @Autowired
    public InvoiceProductRepoTest(InvoiceProductRepository invoiceProductRepo) {
        this.invoiceProductRepo = invoiceProductRepo;
    }

    @BeforeEach
    public void setUp(){
        invoiceProduct = InvoiceProduct.builder()
                .count(0)
                .product(new Product())
                .invoice(new Invoice())
                .build();

        baseId = invoiceProductRepo.save(invoiceProduct);
        assertNotNull(baseId);
    }

    @AfterEach
    public void tearDown(){
        invoiceProductRepo.deleteById(baseId);
        InvoiceProduct proof = invoiceProductRepo.findById(baseId);
        assertNull(proof);
    }

    @Test
    public void testFindInvoiceProductById(){
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(baseId);
        assertEquals(invoiceProduct.getCount(), foundInvoiceProduct.getCount());
    }

    @Test
    public void testFindAllInvoiceProduct(){
        InvoiceProduct newInvoiceProduct = InvoiceProduct.builder()
                .product(null)
                .invoice(null)
                .build();

        InvoiceProductId newId = invoiceProductRepo.save(newInvoiceProduct);
        assertNotNull(newId);

        List<InvoiceProduct> invoiceProducts = invoiceProductRepo.findAll();
        System.out.println(invoiceProducts);
        assertNotNull(invoiceProducts);
        assertTrue(invoiceProducts.size() > 1);
    }

    @Test
    public void testUpdateInvoiceProduct(){
        invoiceProduct.setCount(10);
        invoiceProductRepo.update(invoiceProduct);
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(baseId);
        assertNotNull(foundInvoiceProduct);
        assertEquals(invoiceProduct.getCount().doubleValue(), foundInvoiceProduct.getCount().doubleValue());
    }

    @Test
    public void testExistById(){
        assertTrue(invoiceProductRepo.existsById(baseId));
    }
}
