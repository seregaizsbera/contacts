package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SystemPropertyHandle implements Serializable {
    private Integer id;

    public SystemPropertyHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
