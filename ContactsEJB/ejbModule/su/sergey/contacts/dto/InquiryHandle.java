package su.sergey.contacts.dto;

import java.io.Serializable;

public final class InquiryHandle implements Serializable {
    private String alias;

    public InquiryHandle(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
