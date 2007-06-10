package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SupplyPhonesData implements Serializable, SupplyPhonesCreateInfo {
    private Integer supply;
    private Integer phone;

    public Integer getSupply() {
        return supply;
    }

    public void setSupply(Integer supply) {
        this.supply = supply;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
