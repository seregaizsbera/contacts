package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

public final class ShnipData implements Serializable, ShnipCreateInfo, ShnipUpdateInfo {
    private Integer person;
    private Date graduate;
    private String formLetter;
    private Integer formLeader;
    private String note;

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public Date getGraduate() {
        return graduate;
    }

    public void  setGraduate(Date graduate) {
        this.graduate = graduate;
    }

    public String getFormLetter() {
        return formLetter;
    }

    public void  setFormLetter(String formLetter) {
        this.formLetter = formLetter;
    }

    public Integer getFormLeader() {
        return formLeader;
    }

    public void  setFormLeader(Integer formLeader) {
        this.formLeader = formLeader;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
