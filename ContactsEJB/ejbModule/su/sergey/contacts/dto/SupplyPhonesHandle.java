package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SupplyPhonesHandle implements Serializable {
    private Integer supply;
    private Integer phone;

    public SupplyPhonesHandle(Integer supply, Integer phone) {
        this.supply = supply;
        this.phone = phone;
    }

    public Integer getSupply() {
        return supply;
    }

    public Integer getPhone() {
        return phone;
    }
}
