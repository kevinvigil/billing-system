package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class InvoiceProductRepoTest {

    private final InvoiceProductRepository invoiceProductRepo;

    private static InvoiceProduct invoiceProduct;

    private UUID baseId;

    @Autowired
    public InvoiceProductRepoTest(InvoiceProductRepository invoiceProductRepo) {
        this.invoiceProductRepo = invoiceProductRepo;
    }

    @BeforeEach
    public void setUp(){
        invoiceProduct = InvoiceProduct.builder()
                .amount(0)
                .product(new Product())
                .invoice(new Invoice())
                .build();

        baseId = invoiceProductRepo.save(invoiceProduct);
        assertNotNull(baseId);
        invoiceProduct.setInvoiceproduct_id(baseId);
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
        assertNotNull(foundInvoiceProduct.getInvoiceproduct_id());
        assertEquals(baseId, foundInvoiceProduct.getInvoiceproduct_id());
    }

    @Test
    public void testFindAllInvoiceProduct(){
        InvoiceProduct newInvoiceProduct = InvoiceProduct.builder()
                .product(null)
                .invoice(null)
                .build();

        UUID newId = invoiceProductRepo.save(newInvoiceProduct);
        assertNotNull(newId);

        List<InvoiceProduct> invoiceProducts = invoiceProductRepo.findAll();
        System.out.println(invoiceProducts);
        assertNotNull(invoiceProducts);
        assertTrue(invoiceProducts.size() > 1);
    }

    @Test
    public void testUpdateInvoiceProduct(){
        invoiceProduct.setAmount(10);
        invoiceProductRepo.update(invoiceProduct);
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(baseId);
        assertNotNull(foundInvoiceProduct);
        assertEquals(invoiceProduct.getAmount().doubleValue(), foundInvoiceProduct.getAmount().doubleValue());
    }

    @Test
    public void testExistById(){
        assertTrue(invoiceProductRepo.existsById(baseId));
    }
}
