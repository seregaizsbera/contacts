package su.sergey.contacts.dto;

import java.io.Serializable;

public final class BirthdayHandle implements Serializable {
    private Integer person;

    public BirthdayHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
