package com.system.billingSystem.repository;

import com.system.billingSystem.model.Customer;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface CustomerRepository extends BaseRepository<Customer,Long>{
}
