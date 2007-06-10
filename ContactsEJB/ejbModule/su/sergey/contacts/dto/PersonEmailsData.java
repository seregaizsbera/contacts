package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PersonEmailsData implements Serializable, PersonEmailsCreateInfo, PersonEmailsUpdateInfo {
    private Integer person;
    private Integer email;
    private Boolean basic;

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public Boolean getBasic() {
        return basic;
    }

    public void setBasic(Boolean basic) {
        this.basic = basic;
    }
}
