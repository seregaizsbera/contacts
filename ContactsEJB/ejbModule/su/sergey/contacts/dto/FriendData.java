package su.sergey.contacts.dto;

import java.io.Serializable;

public final class FriendData implements Serializable, FriendCreateInfo, FriendUpdateInfo {
    private Integer person;
    private String description;

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public String getDescription() {
        return description;
    }

    public void  setDescription(String description) {
        this.description = description;
    }
}
