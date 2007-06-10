package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

public final class MsuData implements Serializable, MsuCreateInfo, MsuUpdateInfo {
    private Integer person;
    private Date graduate;
    private Integer department;
    private Boolean hospice;
    private Boolean tutor;
    private String subfaculty;
    private String note;

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Date getGraduate() {
        return graduate;
    }

    public void setGraduate(Date graduate) {
        this.graduate = graduate;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Boolean getHospice() {
        return hospice;
    }

    public void setHospice(Boolean hospice) {
        this.hospice = hospice;
    }

    public Boolean getTutor() {
        return tutor;
    }

    public void setTutor(Boolean tutor) {
        this.tutor = tutor;
    }

    public String getSubfaculty() {
        return subfaculty;
    }

    public void setSubfaculty(String subfaculty) {
        this.subfaculty = subfaculty;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
