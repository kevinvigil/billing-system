package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.InvoiceProduct;
import org.springframework.stereotype.Repository;

@Repository("InvoiceProductRepository")
public interface InvoiceProductRepository extends BaseRepository<InvoiceProduct, Long>{

}
