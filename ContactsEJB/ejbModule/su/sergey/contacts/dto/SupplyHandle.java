package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SupplyHandle implements Serializable {
    private Integer id;

    public SupplyHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
