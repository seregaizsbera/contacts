package su.sergey.contacts.dto;

import java.io.Serializable;

public final class GprsHandle implements Serializable {
    private Integer id;

    public GprsHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
