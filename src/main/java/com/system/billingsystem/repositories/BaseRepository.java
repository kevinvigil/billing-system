package com.system.billingsystem.repositories;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.UUID;

public abstract class BaseRepository<R extends Record, entityClass> {
    
    protected final DSLContext dsl;
    protected final Table<R> table;
    private final Class<entityClass> entityClass;

    protected BaseRepository(DSLContext dsl, Table<R> table, Class<entityClass> entityClass) {
        this.dsl = dsl;
        this.table = table;
        this.entityClass = entityClass;
    }

    public abstract entityClass save(entityClass persisted);
    protected abstract Field<UUID> getIdField();

    public List<entityClass> findAll() {
        return dsl.selectFrom(table).fetchInto(entityClass);
    }

    public entityClass findById(UUID id) {
        return dsl.selectFrom(table)
                .where(getIdField().eq(id))
                .fetchOneInto(entityClass);
    }

    public void deleteAll(){
        dsl.deleteFrom(table).execute();
    }

    public entityClass deleteById(UUID id) {
        return dsl.deleteFrom(table)
                .where(getIdField().eq(id))
                .returning().fetchOneInto(entityClass);
    }

    public boolean existsById(UUID uuid){
        return dsl.fetchExists(dsl.select()
                .from(table).where(getIdField().eq(uuid)));
    }

//    entityClass save(entityClass persisted);
//    entityClass deleteById(ID id);
//    void deleteAll();
//    boolean existsById(ID id);
//    List<entityClass> findAll();
//    entityClass findById(UUID id);

}
