package com.system.billingsystem.entities.microtypes.ids;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseId {
    protected UUID value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseId baseId)) return false;
        return value.equals(baseId.value);
    }
}
