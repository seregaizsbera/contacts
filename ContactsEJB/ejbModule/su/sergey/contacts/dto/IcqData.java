package su.sergey.contacts.dto;

import java.io.Serializable;

public final class IcqData implements Serializable, IcqCreateInfo, IcqUpdateInfo {
    private Integer person;
    private Long icq;
    private String nickname;

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

    public String getNickname() {
        return nickname;
    }

    public void  setNickname(String nickname) {
        this.nickname = nickname;
    }
}
