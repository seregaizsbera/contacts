package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PersonData implements Serializable, PersonCreateInfo, PersonUpdateInfo {
    private Integer id;
    private String first;
    private String second;
    private String last;
    private String address;
    private String note;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void  setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void  setSecond(String second) {
        this.second = second;
    }

    public String getLast() {
        return last;
    }

    public void  setLast(String last) {
        this.last = last;
    }

    public String getAddress() {
        return address;
    }

    public void  setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
