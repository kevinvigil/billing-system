package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("ProductRepository")
public interface ProductRepository extends BaseRepository<Product, UUID>{
}
