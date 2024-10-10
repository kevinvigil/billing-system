package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.microtypes.ids.BaseId;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public abstract class BaseRepository<R extends Record, E> {

    protected final DSLContext dsl;
    protected final Table<R> table;
    private final Class<E> E;

    protected BaseRepository(DSLContext dsl, Table<R> table, Class<E> E) {
        this.dsl = dsl;
        this.table = table;
        this.E = E;
    }

    public abstract BaseId save(E persisted);
    public abstract boolean update(E persisted);

    protected abstract Field<UUID> getIdField();

    public List<E> findAll() {
        return dsl.selectFrom(table).fetchInto(E);
    }

    public E findById(BaseId id) {
        return dsl.selectFrom(table)
                .where(getIdField().eq(id.getValue()))
                .groupBy(getIdField())
                .fetchOneInto(E);
    }

    public E deleteById(BaseId id) {
        return dsl.deleteFrom(table)
                .where(getIdField().eq(id.getValue()))
                .returning().fetchOneInto(E);
    }

    public boolean existsById(BaseId uuid){
        return dsl.fetchExists(dsl.select()
                .from(table).where(getIdField().eq(uuid.getValue())));
    }
}
