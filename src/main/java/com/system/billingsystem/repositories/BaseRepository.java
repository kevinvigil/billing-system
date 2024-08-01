package com.system.billingsystem.repositories;


import com.system.billingsystem.entities.Company;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface BaseRepository<T, ID extends Serializable> {

    T save(T persisted);
    T deleteById(ID id);
    void deleteAll();
    boolean existsById(ID id);
    List<T> findAll();
    T findById(UUID id);

}
