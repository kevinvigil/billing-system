package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("CompanyRepository")
public interface CompanyRepository extends BaseRepository<Company, UUID>{

}
