package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SystemPropertyHandle implements Serializable {
    private String name;

    public SystemPropertyHandle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
