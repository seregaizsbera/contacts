package su.sergey.contacts.dto;

import java.io.Serializable;

public final class CallPayHandle implements Serializable {
    private Integer id;

    public CallPayHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
