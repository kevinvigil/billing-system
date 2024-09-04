package com.system.billingsystem.services;

import com.system.billingsystem.entities.*;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;
import com.system.billingsystem.repositories.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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
                    total += invoiceProduct.getCount() * currentProduct.getPrice().getValue().doubleValue();

                    saveInvoiceProduct(invoiceProduct);
                }
            }
            invoice.setPrice(new InvoicePrice(BigDecimal.valueOf(total)));
            InvoiceId uuid = invoiceRepository.save(invoice);
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
                    total += invoiceProduct.getCount() * currentProduct.getPrice().getValue().doubleValue();

                    this.updateInvoiceProduct(invoiceProduct);
                }
            }
            invoice.setPrice(new InvoicePrice(BigDecimal.valueOf(total)));
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

    public Invoice deleteInvoice (@NotNull InvoiceId id){
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

    public Invoice findInvoiceById(@NotNull InvoiceId id){
        try {
            Invoice invoice = invoiceRepository.findById(id);
            List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findByInvoiceId(id);
            invoice.setProducts(invoiceProductList);
            return invoice;
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

    public ProductId saveProduct(@NotNull Product entity){
        try{
            return productRepository.save(entity);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on InvoiceService in the method saveProduct, message: " + e.getMessage());
            throw e;
        }
    }

    public Product deleteProduct (@NotNull ProductId id){
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

    public Product findProductById (@NotNull ProductId id){
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
