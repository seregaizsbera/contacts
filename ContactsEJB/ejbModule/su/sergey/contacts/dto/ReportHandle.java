package su.sergey.contacts.dto;

import java.io.Serializable;

public final class ReportHandle implements Serializable {
    private Integer id;

    public ReportHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
