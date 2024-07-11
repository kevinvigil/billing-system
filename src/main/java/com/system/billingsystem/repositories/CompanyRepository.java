package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
import org.springframework.stereotype.Repository;

@Repository("CompanyRepository")
public interface CompanyRepository extends BaseRepository<Company, Long>{

}