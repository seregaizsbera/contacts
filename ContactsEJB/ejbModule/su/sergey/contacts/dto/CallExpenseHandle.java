package su.sergey.contacts.dto;

import java.io.Serializable;

public final class CallExpenseHandle implements Serializable {
    private Integer id;

    public CallExpenseHandle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
