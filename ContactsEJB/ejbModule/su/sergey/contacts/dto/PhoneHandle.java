package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PhoneHandle implements Serializable {
    private Integer id;

    public PhoneHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
