package com.system.billingsystem.controllers;

import com.system.billingsystem.DTOs.ProductDto;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.repositories.ProductRepository;
import com.system.billingsystem.services.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final InvoiceService invoiceService;

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(InvoiceService invoiceService, ProductRepository productRepository){
        this.invoiceService = invoiceService;
        this.productRepository = productRepository;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Product entity){
        if (entity == null)
            throw new IllegalArgumentException();

        boolean isNew = entity.getId() == null || !productRepository.existsById(entity.getId());

        ProductDto productDto = invoiceService.saveProduct(entity);

        if (isNew) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(productDto);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ProductDto productDto = invoiceService.deleteProduct(id);
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable Long id){
        try {
            ProductDto productDto = invoiceService.findProductById(id);
            if (productDto != null)
                return ResponseEntity.ok().body(productDto);
            else
                throw new EntityNotFoundException();
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
