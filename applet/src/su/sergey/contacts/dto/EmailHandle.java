package su.sergey.contacts.dto;

import java.io.Serializable;

public final class EmailHandle implements Serializable {
    private Integer id;

    public EmailHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
