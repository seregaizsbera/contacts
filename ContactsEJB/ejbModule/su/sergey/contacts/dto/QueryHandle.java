package su.sergey.contacts.dto;

import java.io.Serializable;

public final class QueryHandle implements Serializable {
    private Integer id;

    public QueryHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
