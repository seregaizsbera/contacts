package su.sergey.contacts.dto;

import java.io.Serializable;

public final class EmailData implements Serializable, EmailCreateInfo, EmailUpdateInfo {
    private Integer id;
    private String email;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void  setEmail(String email) {
        this.email = email;
    }
}
