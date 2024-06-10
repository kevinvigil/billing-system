package com.system.billingSystem.repository;

import com.system.billingSystem.model.Customer;
import org.springframework.stereotype.Repository;

@Repository("CustomerRepository")
public interface CustomerRepository extends BaseRepository<Customer, Long>{

}
