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

    private final InvoiceProductRepository invoiceProductRepository;

    private final ProductRepository productRepository;

    @Autowired
    public InvoiceService(ProductRepository productRepository, InvoiceRepository invoiceRepository,
                          InvoiceProductRepository invoiceProductRepository){
        this.productRepository = productRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice saveInvoice(@NotNull Invoice invoice){
        try{
            List<InvoiceProduct> productList = invoice.getProducts();
            double total = 0.0;
            if (productList != null && !productList.isEmpty()){
                for (InvoiceProduct invoiceProduct:productList) {
                    invoiceProduct.setInvoice(invoice);

                    Product currentProduct = this.findProductById(invoiceProduct.getProduct().getProductId());
                    total += invoiceProduct.getAmount() * currentProduct.getPrice().doubleValue();

                    saveInvoiceProduct(invoiceProduct);
                }
            }
            invoice.setTotal(BigDecimal.valueOf(total));
            UUID uuid = invoiceRepository.save(invoice);
            return invoiceRepository.findById(uuid);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveInvoice, message: " + e.getMessage());
            throw e;
        }
    }

    public Invoice updateInvoice (@NotNull Invoice invoice){
        try {
            double total = 0.0;
            List<InvoiceProduct> productList = invoice.getProducts();
            if (productList != null && !productList.isEmpty()){
                for (InvoiceProduct invoiceProduct:productList) {
                    invoiceProduct.setInvoice(invoice);

                    Product currentProduct = this.findProductById(invoiceProduct.getProduct().getProductId());
                    total += invoiceProduct.getAmount() * currentProduct.getPrice().doubleValue();

                    this.updateInvoiceProduct(invoiceProduct);
                }
            }
            invoice.setTotal(BigDecimal.valueOf(total));
            if (this.invoiceRepository.update(invoice)){
                return this.invoiceRepository.findById(invoice.getInvoiceId());
            } else {
                return null;
            }
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

    public List<Invoice> findAllInvoices(){
        try {
            return this.invoiceRepository.findAll();
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findAllInvoices, message: " + e.getMessage());
            throw e;
        }
    }

    public void saveInvoiceProduct(@NotNull InvoiceProduct invoiceProduct){
        try{
            if (invoiceProductRepository.save(invoiceProduct) == null)
                logger.log(Level.SEVERE, "InvoiceProduct was not saved");
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveInvoiceProduct, message: " + e.getMessage());
            throw e;
        }
    }

    public void updateInvoiceProduct(@NotNull InvoiceProduct invoiceProduct){
        try{
            if (!this.invoiceProductRepository.update(invoiceProduct))
                logger.log(Level.SEVERE, "InvoiceProduct was not updated");
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method updateInvoiceProduct, message: " + e.getMessage());
            throw e;
        }
    }

    public Product saveProduct(@NotNull Product entity){
        try{
            UUID uuid = productRepository.save(entity);
            if(uuid != null)
                return productRepository.findById(uuid);
            return null;
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

    public List<Product> findAllProducts(){
        try {
            List<Product> g = this.productRepository.findAll();
            System.out.println(g.size());
            return g;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findAllInvoices, message: " + e.getMessage());
            throw e;
        }
    }

    public Product updateProductById (@NotNull Product product){
        try{
            if (this.productRepository.update(product))
                return this.productRepository.findById(product.getProductId());
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method findProductById, message: " + e.getMessage());
            throw e;
        }
    }
}
