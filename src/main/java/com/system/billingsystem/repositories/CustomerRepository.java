package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface CustomerRepository extends BaseRepository<Customer,Long>{
}
