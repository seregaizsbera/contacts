package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PersonPhonesData implements Serializable, PersonPhonesCreateInfo, PersonPhonesUpdateInfo {
    private Integer person;
    private Integer phone;

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public Integer getPhone() {
        return phone;
    }

    public void  setPhone(Integer phone) {
        this.phone = phone;
    }
}
