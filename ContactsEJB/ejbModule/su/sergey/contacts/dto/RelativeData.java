package su.sergey.contacts.dto;

import java.io.Serializable;

public final class RelativeData implements Serializable, RelativeCreateInfo, RelativeUpdateInfo {
    private Integer person;
    private String relationship;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void  setDescription(String description) {
        this.description = description;
    }
}
