package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

public final class MsuData implements Serializable, MsuCreateInfo, MsuUpdateInfo {
    private Integer person;
    private Date graduate;
    private Integer department;
    private Boolean hospice;
    private String subfaculty;

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

    public Integer getDepartment() {
        return department;
    }

    public void  setDepartment(Integer department) {
        this.department = department;
    }

    public Boolean getHospice() {
        return hospice;
    }

    public void  setHospice(Boolean hospice) {
        this.hospice = hospice;
    }

    public String getSubfaculty() {
        return subfaculty;
    }

    public void  setSubfaculty(String subfaculty) {
        this.subfaculty = subfaculty;
    }
}
