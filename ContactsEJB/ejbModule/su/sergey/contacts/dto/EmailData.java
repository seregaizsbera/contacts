package su.sergey.contacts.dto;

import java.io.Serializable;

public final class EmailData implements Serializable, EmailCreateInfo, EmailUpdateInfo {
    private Integer id;
    private Integer person;
    private String email;
    private Boolean basic;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void  setEmail(String email) {
        this.email = email;
    }

    public Boolean getBasic() {
        return basic;
    }

    public void  setBasic(Boolean basic) {
        this.basic = basic;
    }
}
