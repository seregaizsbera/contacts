package su.sergey.contacts.dto;

import java.io.Serializable;

public final class ShnipHandle implements Serializable {
    private Integer person;

    public ShnipHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
