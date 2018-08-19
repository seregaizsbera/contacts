package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PersonHandle implements Serializable {
    private Integer id;

    public PersonHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
