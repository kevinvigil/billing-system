package com.system.billingSystem.repository;

import com.system.billingSystem.model.Product;
import org.springframework.stereotype.Repository;

@Repository("ProductRepository")
public interface ProductRepository extends BaseRepository<Product, Long>{
}
