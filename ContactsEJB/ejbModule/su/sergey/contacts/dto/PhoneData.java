package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PhoneData implements Serializable, PhoneCreateInfo, PhoneUpdateInfo {
    private Integer id;
    private String phone;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void  setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void  setType(Integer type) {
        this.type = type;
    }
}
