package su.sergey.contacts.dto;

import java.io.Serializable;

public final class RelativeHandle implements Serializable {
    private Integer person;

    public RelativeHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
