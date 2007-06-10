package su.sergey.contacts.dto;

import java.io.Serializable;

public final class AddressData implements Serializable, AddressCreateInfo, AddressUpdateInfo {
    private Integer person;
    private String address;

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
