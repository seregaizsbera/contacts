package su.sergey.contacts.dto;

import java.io.Serializable;

public final class RelatedData implements Serializable, RelatedCreateInfo, RelatedUpdateInfo {
    private Integer person;
    private String relationship;
    private String note;

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public String getRelationship() {
        return relationship;
    }

    public void  setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
