package su.sergey.contacts.dto;

import java.io.Serializable;

public final class PersonData implements Serializable, PersonCreateInfo, PersonUpdateInfo {
    private Integer id;
    private String first;
    private String middle;
    private String last;
    private Integer gender;
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

    public String getMiddle() {
        return middle;
    }

    public void  setMiddle(String middle) {
        this.middle = middle;
    }

    public String getLast() {
        return last;
    }

    public void  setLast(String last) {
        this.last = last;
    }

    public Integer getGender() {
        return gender;
    }

    public void  setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
