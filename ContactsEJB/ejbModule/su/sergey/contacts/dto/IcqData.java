package su.sergey.contacts.dto;

import java.io.Serializable;

public final class IcqData implements Serializable, IcqCreateInfo, IcqUpdateInfo {
    private Integer person;
    private Long icq;
    private String alias;

    public Integer getPerson() {
        return person;
    }

    public void  setPerson(Integer person) {
        this.person = person;
    }

    public Long getIcq() {
        return icq;
    }

    public void  setIcq(Long icq) {
        this.icq = icq;
    }

    public String getAlias() {
        return alias;
    }

    public void  setAlias(String alias) {
        this.alias = alias;
    }
}
