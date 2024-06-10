package com.system.billingSystem.repository;

import com.system.billingSystem.model.InvoiceProduct;
import org.springframework.stereotype.Repository;

@Repository("InvoiceProductRepository")
public interface InvoiceProductRepository extends BaseRepository<InvoiceProduct, Long>{

}
