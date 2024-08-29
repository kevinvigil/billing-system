package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.dto.InvoiceDto;
import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.*;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;

import java.util.ArrayList;
import java.util.List;

import static com.system.billingsystem.dto.dtosmappers.CompanyMapper.COMPANY_MAPPER;

public class InvoiceDtoMapper {

    private InvoiceDtoMapper() {}

    public static InvoiceDto toDto(Invoice invoice) {
        List<InvoiceProduct> productList = invoice.getProducts();
        List <InvoiceProductDto> products = new ArrayList<>();

        if (productList != null && !productList.isEmpty()){
            for (InvoiceProduct ip:productList) {
                products.add(InvoiceProductDtoMapper.toDto(ip));
            }
        }

        CompanyDto sellerCompany = null;
        CompanyDto buyerCompany = null;

        if (invoice.getSellerCompany() != null)
            sellerCompany = COMPANY_MAPPER.toDto(invoice.getSellerCompany());

        if (invoice.getBuyerCompany() != null)
            buyerCompany = COMPANY_MAPPER.toDto(invoice.getBuyerCompany());

        return new InvoiceDto(
                invoice.getInvoiceId().getValue(),
                invoice.getDate(),
                invoice.isPaid(),
                invoice.isInvoiced(),
                invoice.getInvoicePrice().getValue(),
                (invoice.getInvoicevoucher() == null)? null:invoice.getInvoicevoucher().name(),
                (invoice.getCategory() == null)? null : invoice.getCategory().name(),
                sellerCompany,
                buyerCompany,
                products
        );
    }

    public static Invoice toDomain(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();

        invoice.setInvoiceId(new InvoiceId(invoiceDto.invoiceId()));
        invoice.setPaid(invoiceDto.paid());
        invoice.setInvoiced(invoiceDto.invoiced());
        invoice.setInvoicePrice(new InvoicePrice(invoiceDto.total()));
        invoice.setInvoicevoucher(InvoiceVoucher.valueOf(invoiceDto.invoiceVoucher()));
        invoice.setCategory(InvoiceCategory.valueOf(invoiceDto.type()));
        invoice.setSellerCompany(COMPANY_MAPPER.toDomain(invoiceDto.sellerCompany()));
        invoice.setBuyerCompany(COMPANY_MAPPER.toDomain(invoiceDto.buyerCompany()));

        return invoice;
    }
}
