package com.system.billingsystem.services;

import com.system.billingsystem.entities.*;
import com.system.billingsystem.repositories.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
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

    public Invoice saveInvoice(@NotNull Invoice invoice){
        try{
            List<InvoiceProduct> productList = invoice.getProducts();
            double total = 0.0;
            if (productList != null && !productList.isEmpty()){
                for (InvoiceProduct invoiceProduct:productList) {
                    invoiceProduct.setInvoice(invoice);

                    total += invoiceProduct.getAmount().doubleValue() * this.findProductById(invoiceProduct.getProduct().getProduct_id()).getPrice().doubleValue();

                    saveInvoiceProduct(invoiceProduct);
                }
            }
            invoice.setTotal(new BigDecimal(total));
            return invoiceRepository.save(invoice);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveInvoice, message: " + e.getMessage());
            throw e;
        }
    }

    // TODO refactor
    public Invoice updateInvoice (@NotNull Invoice invoice){
        try {
            this.deleteInvoice(invoice.getInvoice_id());

//            InvoiceDto newInvoiceDto = new InvoiceDto(null, invoiceDto.date(), invoiceDto.paid(), invoiceDto.invoiced(), invoiceDto.total(),
//                    invoiceDto.invoiceVoucher(), invoiceDto.type(), invoiceDto.sellerCompany(), invoiceDto.buyerCompany(), invoiceDto.products());

            return this.saveInvoice(invoice);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method updateInvoiceById, message: " + e.getMessage());
            throw e;
        }
    }

    public Invoice deleteInvoice (@NotNull UUID id){
        try {
            Invoice invoice = this.findInvoiceById(id);
            if (invoice != null)
                invoiceRepository.deleteById(id);
            return  invoice;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method deleteInvoice, message: " + e.getMessage());
            throw e;
        }
    }

    public Invoice findInvoiceById(@NotNull UUID id){
        try {
            return invoiceRepository.findById(id);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findInvoiceById, message: " + e.getMessage());
            throw e;
        }
    }

    public void saveInvoiceProduct(@NotNull InvoiceProduct invoiceProduct){
        try{
            invoiceProductRepository.save(invoiceProduct);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveInvoiceProduct, message: " + e.getMessage());
            throw e;
        }
    }

    public Product saveProduct(@NotNull Product product){
        try{
            return productRepository.save(product);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveProduct, message: " + e.getMessage());
            throw e;
        }
    }

    public Product deleteProduct (@NotNull UUID id){
        try{
            Product product= this.findProductById(id);
            if (product != null)
                productRepository.deleteById(id);
            return product;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById, message: " + e.getMessage());
            throw e;
        }
    }

    public Product findProductById (@NotNull UUID id){
        try{
            return this.productRepository.findById(id);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById, message: " + e.getMessage());
            throw e;
        }
    }

    public Product updateProductById (@NotNull Product product){
        try{
            return this.productRepository.save(product);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById, message: " + e.getMessage());
            throw e;
        }
    }
}
