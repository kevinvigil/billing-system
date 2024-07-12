package com.system.billingsystem.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends org.springframework.data.repository.Repository<T,ID> {

    T save(T persisted);
    void saveAll(Iterable<T> entities);
    void deleteById(ID id);
    void deleteAll();
    boolean existsById(ID id);
    List<T> findAll();
    Optional<T> findById(UUID id);

}