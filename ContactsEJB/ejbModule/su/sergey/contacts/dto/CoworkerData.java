package su.sergey.contacts.dto;

import java.io.Serializable;

public final class CoworkerData implements Serializable, CoworkerCreateInfo, CoworkerUpdateInfo {
    private Integer person;
    private String job;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void  setDescription(String description) {
        this.description = description;
    }
}
