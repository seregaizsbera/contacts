package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PersonEmailsHandle implements Serializable {
    private Integer person;
    private Integer email;

    public PersonEmailsHandle(Integer person, Integer email) {
        this.person = person;
        this.email = email;
    }

    public Integer getPerson() {
        return person;
    }

    public Integer getEmail() {
        return email;
    }
}
