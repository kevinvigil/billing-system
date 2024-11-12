package com.billingsystem.mainapp.entities.microtypes.ids;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Id {
    protected UUID value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id id)) return false;
        return value.equals(id.value);
    }
}
