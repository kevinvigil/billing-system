package com.system.billingsystem.repositories;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.UUID;

public abstract class BaseRepository<R extends Record, E> {
    
    protected final DSLContext dsl;
    protected final Table<R> table;
    private final Class<E> E;

    protected BaseRepository(DSLContext dsl, Table<R> table, Class<E> E) {
        this.dsl = dsl;
        this.table = table;
        this.E = E;
    }

    public abstract UUID save(E persisted);
    public abstract boolean update(E persisted);
    protected abstract Field<UUID> getIdField();

    public List<E> findAll() {
        return dsl.selectFrom(table).fetchInto(E);
    }

    public E findById(UUID id) {
        return dsl.selectFrom(table)
                .where(getIdField().eq(id))
                .fetchOneInto(E);
    }

    public E deleteById(UUID id) {
        return dsl.deleteFrom(table)
                .where(getIdField().eq(id))
                .returning().fetchOneInto(E);
    }

    public boolean existsById(UUID uuid){
        return dsl.fetchExists(dsl.select()
                .from(table).where(getIdField().eq(uuid)));
    }

}
