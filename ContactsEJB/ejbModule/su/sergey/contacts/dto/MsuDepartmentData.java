package su.sergey.contacts.dto;

import java.io.Serializable;

public final class MsuDepartmentData implements Serializable, MsuDepartmentCreateInfo, MsuDepartmentUpdateInfo {
    private Integer id;
    private String shortName;
    private String department;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void  setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDepartment() {
        return department;
    }

    public void  setDepartment(String department) {
        this.department = department;
    }
}
