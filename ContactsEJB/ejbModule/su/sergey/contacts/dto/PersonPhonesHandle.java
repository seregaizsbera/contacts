package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PersonPhonesHandle implements Serializable {
    private Integer person;
    private Integer phone;

    public PersonPhonesHandle(Integer person, Integer phone) {
        this.person = person;
        this.phone = phone;
    }

    public Integer getPerson() {
        return person;
    }

    public Integer getPhone() {
        return phone;
    }
}
