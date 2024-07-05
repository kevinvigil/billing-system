package com.system.billingSystem.repository;

import com.system.billingSystem.model.Company;
import org.springframework.stereotype.Repository;

@Repository("CompanyRepository")
public interface CompanyRepository extends BaseRepository<Company, Long>{

}
