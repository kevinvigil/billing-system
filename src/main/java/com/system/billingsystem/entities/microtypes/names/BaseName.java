package com.system.billingsystem.entities.microtypes.names;

import lombok.Data;

@Data
public class BaseName {
    protected String name;

    public BaseName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseName baseName)) return false;
        return name.equals(baseName.name);
    }
}
