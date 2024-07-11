package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Product;
import org.springframework.stereotype.Repository;

@Repository("ProductRepository")
public interface ProductRepository extends BaseRepository<Product, Long>{
}
