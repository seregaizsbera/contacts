package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

public final class SupplyData implements Serializable, SupplyCreateInfo, SupplyUpdateInfo {
    private Integer id;
    private String name;
    private String shortName;
    private String parentName;
    private Integer kind;
    private String address;
    private String url;
    private String inn;
    private String metro;
    private Boolean important;
    private String note;
    private Date insertTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void  setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void  setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getParentName() {
        return parentName;
    }

    public void  setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getKind() {
        return kind;
    }

    public void  setKind(Integer kind) {
        this.kind = kind;
    }

    public String getAddress() {
        return address;
    }

    public void  setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void  setUrl(String url) {
        this.url = url;
    }

    public String getInn() {
        return inn;
    }

    public void  setInn(String inn) {
        this.inn = inn;
    }

    public String getMetro() {
        return metro;
    }

    public void  setMetro(String metro) {
        this.metro = metro;
    }

    public Boolean getImportant() {
        return important;
    }

    public void  setImportant(Boolean important) {
        this.important = important;
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
