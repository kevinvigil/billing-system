package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProductRepoTest {

    @Autowired
    private ProductRepository productRepo;

    private static Product product;
    
    private UUID baseId;

    @BeforeEach
    public void setUp(){
        product = Product.builder()
                .name("Name Product")
                .description("Description Product")
                .price(BigDecimal.valueOf(100.0))
                .build();

        baseId = productRepo.save(product);
        assertNotNull(baseId);
        product.setProduct_id(baseId);
    }

    @AfterEach
    public void tearDown(){
        productRepo.deleteById(baseId);
        Product proof = productRepo.findById(baseId);
        assertNull(proof);
    }
    
    @Test
    public void testFindProductById(){
        Product foundProduct = productRepo.findById(baseId)   ;
        assertNotNull(foundProduct);
        assertEquals(baseId, foundProduct.getProduct_id());
    }

    @Test
    public void testUpdateProduct(){
        product.setName("Updated Name");
        productRepo.update(product);
        Product updatedProduct = productRepo.findById(baseId)   ;
        assertNotNull(updatedProduct);
        assertEquals(updatedProduct.getName(), product.getName());
    }

    @Test
    public void testFindAllProducts(){
        Product newProduct = Product.builder()
                .name("Name Product 2")
                .description("Description Product 2")
                .price(BigDecimal.valueOf(200.0))
                .build();

        assertNotNull(productRepo.save(newProduct));

        List<Product> products = productRepo.findAll();
        assertNotNull(products);
        assertTrue(products.size()>1);
    }

    @Test
    public void testExistById(){
        assertTrue(productRepo.existsById(baseId));
    }
}
