package su.sergey.contacts.dto;

import java.io.Serializable;

public final class MsuDepartmentHandle implements Serializable {
    private Integer id;

    public MsuDepartmentHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
