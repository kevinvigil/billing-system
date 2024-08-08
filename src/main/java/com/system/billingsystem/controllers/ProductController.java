package com.system.billingSystem.controllers;

import com.system.billingSystem.dto.ProductDto;
import com.system.billingSystem.dto.dtosmappers.ProductDtoMapper;
import com.system.billingSystem.entities.Product;
import com.system.billingSystem.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final InvoiceService invoiceService;

    @Autowired
    public ProductController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody ProductDto entity){
        if (entity == null)
            throw new IllegalArgumentException();

        Product product = invoiceService.saveProduct(ProductDtoMapper.toDomain(entity));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductDtoMapper.toDto(product));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody ProductDto entity){
        if (entity == null)
            throw new IllegalArgumentException();

        Product product = invoiceService.updateProductById(ProductDtoMapper.toDomain(entity));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductDtoMapper.toDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Product product = invoiceService.deleteProduct(id);
        return ResponseEntity.ok().body(ProductDtoMapper.toDto(product));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            Product product = invoiceService.findProductById(id);
            if (product != null)
                return ResponseEntity.ok().body(ProductDtoMapper.toDto(product));
            return null;
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        try {
            List<ProductDto> productDtos = this.invoiceService.findAllProducts().stream().map(ProductDtoMapper::toDto).toList();
            return ResponseEntity.ok().body(productDtos);
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
