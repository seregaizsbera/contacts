package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SupplyEmailsData implements Serializable, SupplyEmailsCreateInfo, SupplyEmailsUpdateInfo {
    private Integer supply;
    private Integer email;

    public Integer getSupply() {
        return supply;
    }

    public void  setSupply(Integer supply) {
        this.supply = supply;
    }

    public Integer getEmail() {
        return email;
    }

    public void  setEmail(Integer email) {
        this.email = email;
    }
}
