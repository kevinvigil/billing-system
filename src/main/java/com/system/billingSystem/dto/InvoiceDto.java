package com.system.billingSystem.dto;

import com.system.billingSystem.model.Invoice;
import com.system.billingSystem.model.InvoiceProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class InvoiceDto {

    private Long id;
    private OffsetDateTime date;
    private boolean paid;
    private boolean invoiced;
    private double total;
    private String InvoiceVoucher;
    private String type;
    private Long company;
    private Long customer;
    private List<InvoiceProductDto> products;

    public InvoiceDto (@NotNull Invoice i){
        this.id = i.getId();
        this.date = i.getDate();
        this.paid = i.isPaid();
        this.invoiced = i.isInvoiced();
        this.total = i.getTotal();
        this.InvoiceVoucher = i.getInvoiceVoucher().name();
        this.type = i.getType().name();
        this.company = i.getCompany().getId();
        this.customer = i.getCustomer().getId();
        List<InvoiceProduct> productList = i.getProducts();
        if (productList != null){
            if (this.products == null){
                this.products = new ArrayList<InvoiceProductDto>();
            }
            for (InvoiceProduct IP:productList) {
                this.products.add(new InvoiceProductDto(IP));
            }
        }
    }

    @Override
    public String toString() {
        return "YourClassName{" +
                "id=" + id +
                ", date=" + date +
                ", paid=" + paid +
                ", invoiced=" + invoiced +
                ", total=" + total +
                ", InvoiceVoucher='" + InvoiceVoucher + '\'' +
                ", type='" + type + '\'' +
                ", company=" + company +
                ", customer=" + customer +
                '}';
    }

}
