package su.sergey.contacts.dto;

import java.io.Serializable;

public final class RelatedHandle implements Serializable {
    private Integer person;

    public RelatedHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
