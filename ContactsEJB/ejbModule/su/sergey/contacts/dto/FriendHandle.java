package su.sergey.contacts.dto;

import java.io.Serializable;

public final class FriendHandle implements Serializable {
    private Integer person;

    public FriendHandle(Integer person) {
        this.person = person;
    }

    public Integer getPerson() {
        return person;
    }
}
