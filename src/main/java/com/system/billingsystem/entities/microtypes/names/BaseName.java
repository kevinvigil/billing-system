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
}
