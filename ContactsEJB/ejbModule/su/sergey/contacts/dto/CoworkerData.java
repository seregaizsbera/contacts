package su.sergey.contacts.dto;

import java.io.Serializable;

public final class CoworkerData implements Serializable, CoworkerCreateInfo, CoworkerUpdateInfo {
    private Integer person;
    private String job;
    private String note;

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public String getJob() {
        return job;
    }

    public void  setJob(String job) {
        this.job = job;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
