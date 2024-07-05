package com.system.billingSystem.repository;

import com.system.billingSystem.model.User;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends BaseRepository<User,Long>{
}
