package su.sergey.contacts.dto;

import java.io.Serializable;

public final class ReportData implements Serializable, ReportCreateInfo, ReportUpdateInfo {
    private Integer id;
    private String name;
    private String builder;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void  setName(String name) {
        this.name = name;
    }

    public String getBuilder() {
        return builder;
    }

    public void  setBuilder(String builder) {
        this.builder = builder;
    }
}
