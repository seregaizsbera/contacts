package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

public final class BirthdayData implements Serializable, BirthdayCreateInfo, BirthdayUpdateInfo {
    private Integer person;
    private Date birthday;
    private Date birthyear;

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void  setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthyear() {
        return birthyear;
    }

    public void  setBirthyear(Date birthyear) {
        this.birthyear = birthyear;
    }
}
