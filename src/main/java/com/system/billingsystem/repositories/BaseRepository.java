package com.system.billingsystem.repositories;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseRepository<T, ID extends Serializable> {

    T save(T persisted);
    void saveAll(Iterable<T> entities);
    void deleteById(ID id);
    void deleteAll();
    boolean existsById(ID id);
    List<T> findAll();
    Optional<T> findById(UUID id);

}
