package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SupplyPhonesData implements Serializable, SupplyPhonesCreateInfo, SupplyPhonesUpdateInfo {
    private Integer supply;
    private Integer phone;
    private String note;

    public Integer getSupply() {
        return supply;
    }

    public void  setSupply(Integer supply) {
        this.supply = supply;
    }

    public Integer getPhone() {
        return phone;
    }

    public void  setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
