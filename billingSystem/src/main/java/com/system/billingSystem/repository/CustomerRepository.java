package com.system.billingSystem.repository;

import com.system.billingSystem.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("CustomerRepository")
public interface CustomerRepository extends BaseRepository<Customer, Long>{

    @Query(
            "update Customer c set c.name = :name, c.direction = :direction, c.email = :email, c.phone = :phone where c.id = :id"
    )
    Customer updateById(@Param("id") Long id,
                        @Param("name") String name,
                        @Param("direction") String direction,
                        @Param("email") String email,
                        @Param("phone") String phone);

}
