package su.sergey.contacts.dto;

import java.io.Serializable;

public final class CallHandle implements Serializable {
    private Integer id;

    public CallHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
