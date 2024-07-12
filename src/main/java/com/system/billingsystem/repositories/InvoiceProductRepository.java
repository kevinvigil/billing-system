package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.InvoiceProduct;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("InvoiceProductRepository")
public interface InvoiceProductRepository extends BaseRepository<InvoiceProduct, UUID>{

}
