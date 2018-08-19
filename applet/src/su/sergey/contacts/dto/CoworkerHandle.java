package su.sergey.contacts.dto;

import java.io.Serializable;

public final class CoworkerHandle implements Serializable {
    private Integer person;

    public CoworkerHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
