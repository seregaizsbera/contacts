package su.sergey.contacts.dto;

import java.io.Serializable;

public final class MsuHandle implements Serializable {
    private Integer person;

    public MsuHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
