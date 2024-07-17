package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("CustomerRepository")
public interface CustomerRepository extends BaseRepository<Customer, UUID>{

}
