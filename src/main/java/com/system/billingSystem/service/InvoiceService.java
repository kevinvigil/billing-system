package com.system.billingSystem.service;

import com.system.billingSystem.dto.InvoiceDto;
import com.system.billingSystem.dto.InvoiceProductDto;
import com.system.billingSystem.dto.ProductDto;
import com.system.billingSystem.model.*;
import com.system.billingSystem.repository.*;
import jakarta.transaction.Transactional;
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

    private final CustomerRepository customerRepository;

    private final InvoiceProductRepository invoiceProductRepository;

    private final ProductRepository productRepository;

    @Autowired
    public InvoiceService(ProductRepository productRepository,
                          InvoiceRepository invoiceRepository,
                          InvoiceProductRepository invoiceProductRepository,
                          CompanyRepository companyRepository,
                          CustomerRepository customerRepository){
        this.productRepository = productRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public InvoiceDto saveInvoice(InvoiceDto invoiceDto){
        try{

            Invoice i=  invoiceRepository.save(Invoice.newInvoice(invoiceDto));
            List<InvoiceProductDto> productList = invoiceDto.getProducts();
            if (productList != null){
                for (InvoiceProductDto invoiceProductDto:productList) {
                    invoiceProductDto.setIdInvoice(i.getId());

                    InvoiceProduct invoiceProduct = new InvoiceProduct(invoiceProductDto);

                    saveInvoiceProduct(invoiceProduct);
                }
            }

            return new InvoiceDto(i);

        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveInvoice");
            throw e;
        }
    }

    @Transactional
    public boolean updateInvoiceById (InvoiceDto invoiceDto){
        try {
            Invoice invoice = Invoice.newInvoice(invoiceDto);
            this.invoiceRepository.updateInvoiceById(invoice.getId(),
                    invoice.getDate(), invoice.isPaid(), invoice.isInvoiced(), invoice.getTotal(), invoice.getInvoiceVoucher(),
                    invoice.getType(), invoice.getCompany(), invoice.getCustomer());

            return true;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method updateInvoiceById");
            throw e;
        }
    }

    @Transactional
    public InvoiceDto deleteInvoice (Long id){
        try {
            InvoiceDto invoiceDto = this.findInvoiceById(id);
            if (invoiceDto != null)
                invoiceRepository.deleteById(id);
            return  invoiceDto;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method deleteInvoice");
            throw e;
        }
    }

    public InvoiceDto findInvoiceById(Long id){
        try {
            Invoice invoice = invoiceRepository.findById(id).orElse(null);
            if (invoice!=null)
                return new InvoiceDto(invoice);
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findInvoiceById");
            throw e;
        }
    }

    @Transactional
    public void saveInvoiceProduct(InvoiceProduct invoiceProduct){
        try{
            invoiceProductRepository.save(invoiceProduct);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveInvoiceProduct");
            throw e;
        }
    }

    @Transactional
    public ProductDto saveProduct(Product data){
        try{
            return new ProductDto(productRepository.save(data));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveProduct");
            throw e;
        }
    }

    @Transactional
    public ProductDto deleteProduct (Long id){
        try{
            ProductDto productDto= this.findProductById(id);
            if (productDto != null)
                productRepository.deleteById(id);
            return productDto;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById");
            throw e;
        }
    }

    public ProductDto findProductById (Long id){
        try{
            Product product= this.productRepository.findById(id).orElse(null);
            if (product != null)
                return new ProductDto(product);
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById");
            throw e;
        }
    }
}
