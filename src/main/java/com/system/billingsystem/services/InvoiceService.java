package com.system.billingsystem.services;

import com.system.billingsystem.DTOs.InvoiceDto;
import com.system.billingsystem.DTOs.InvoiceProductDto;
import com.system.billingsystem.DTOs.ProductDto;
import com.system.billingsystem.entities.*;
import com.system.billingsystem.repositories.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("InvoiceService")
public class InvoiceService{

    private static final Logger logger = Logger.getLogger(InvoiceService.class.getName());

    private final InvoiceRepository invoiceRepository;

    private final CompanyRepository companyRepository;

    private final InvoiceProductRepository invoiceProductRepository;

    private final ProductRepository productRepository;

    @Autowired
    public InvoiceService(ProductRepository productRepository,
                          InvoiceRepository invoiceRepository,
                          InvoiceProductRepository invoiceProductRepository,
                          CompanyRepository companyRepository){
        this.productRepository = productRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public InvoiceDto saveInvoice(@NotNull InvoiceDto invoiceDto){
        try{
            Invoice invoice = Invoice.newInvoice(invoiceDto);
            List<InvoiceProductDto> productList = invoiceDto.products();
            double total = 0.0;
            if (productList != null && !productList.isEmpty()){
                for (InvoiceProductDto invoiceProductDto:productList) {
                    InvoiceProduct invoiceProduct = new InvoiceProduct(invoiceProductDto);
                    invoiceProduct.setInvoice(invoice);

                    total += invoiceProduct.getAmount() * this.findProductById(invoiceProduct.getProduct().getId()).price();

                    saveInvoiceProduct(invoiceProduct);
                }
            }
            invoice.setTotal(total);
            System.out.println("invoice "+invoice);
            Invoice aux = invoiceRepository.save(invoice);
            System.out.println("aux "+aux);

            return InvoiceDto.newInvoiceDto(aux);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveInvoice, message: " + e.getMessage());
            throw e;
        }
    }

    // TODO refactor
    @Transactional
    public InvoiceDto updateInvoice (@NotNull InvoiceDto invoiceDto){
        try {
            this.deleteInvoice(invoiceDto.id());

//            InvoiceDto newInvoiceDto = new InvoiceDto(null, invoiceDto.date(), invoiceDto.paid(), invoiceDto.invoiced(), invoiceDto.total(),
//                    invoiceDto.invoiceVoucher(), invoiceDto.type(), invoiceDto.sellerCompany(), invoiceDto.buyerCompany(), invoiceDto.products());

            return this.saveInvoice(invoiceDto);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method updateInvoiceById, message: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    public InvoiceDto deleteInvoice (@NotNull Long id){
        try {
            InvoiceDto invoiceDto = this.findInvoiceById(id);
            if (invoiceDto != null)
                invoiceRepository.deleteById(id);
            return  invoiceDto;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method deleteInvoice, message: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    public InvoiceDto findInvoiceById(@NotNull Long id){
        try {
            Invoice invoice = invoiceRepository.findById(id).orElse(null);
            if (invoice!=null)
                return InvoiceDto.newInvoiceDto(invoice);
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findInvoiceById, message: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void saveInvoiceProduct(@NotNull InvoiceProduct invoiceProduct){
        try{
            invoiceProductRepository.save(invoiceProduct);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveInvoiceProduct, message: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductDto saveProduct(@NotNull Product product){
        try{
            return new ProductDto (productRepository.save(product));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveProduct, message: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductDto deleteProduct (@NotNull Long id){
        try{
            ProductDto productDto= this.findProductById(id);
            if (productDto != null)
                productRepository.deleteById(id);
            return productDto;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById, message: " + e.getMessage());
            throw e;
        }
    }

    public ProductDto findProductById (@NotNull Long id){
        try{
            Product product= this.productRepository.findById(id).orElse(null);
            if (product != null)
                return new ProductDto (product);
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById, message: " + e.getMessage());
            throw e;
        }
    }

    public ProductDto updateProductById (@NotNull Product product){
        try{
            return new ProductDto(this.productRepository.save(product));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById, message: " + e.getMessage());
            throw e;
        }
    }
}
