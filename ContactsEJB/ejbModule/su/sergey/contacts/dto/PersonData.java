package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

public final class PersonData implements Serializable, PersonCreateInfo, PersonUpdateInfo {
    private Integer id;
    private String first;
    private String middle;
    private String last;
    private Integer gender;
    private String note;
    private Date insertTime;
    private Date updateTime;

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

    public Date getInsertTime() {
        return insertTime;
    }

    public void  setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void  setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
