package su.sergey.contacts.dto;

import java.io.Serializable;

public final class AddressHandle implements Serializable {
    private Integer person;

    public AddressHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
