package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SupplyEmailsHandle implements Serializable {
    private Integer supply;
    private Integer email;

    public SupplyEmailsHandle(Integer supply, Integer email) {
        this.supply = supply;
        this.email = email;
    }

    public Integer getSupply() {
        return supply;
    }

    public Integer getEmail() {
        return email;
    }
}
