package su.sergey.contacts.dto;

import java.io.Serializable;

public final class FriendData implements Serializable, FriendCreateInfo, FriendUpdateInfo {
    private Integer person;
    private String note;

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
