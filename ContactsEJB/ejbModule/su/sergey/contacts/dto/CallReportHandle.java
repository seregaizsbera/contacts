package su.sergey.contacts.dto;

import java.io.Serializable;

public final class CallReportHandle implements Serializable {
    private Integer id;

    public CallReportHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
