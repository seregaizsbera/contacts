package su.sergey.contacts.dto;

import java.io.Serializable;

public final class IcqHandle implements Serializable {
    private Integer person;

    public IcqHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
