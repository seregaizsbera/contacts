package su.sergey.contacts.dto;

import java.io.Serializable;

public final class SupplyData implements Serializable, SupplyCreateInfo, SupplyUpdateInfo {
    private Integer id;
    private String name;
    private Integer kind;
    private String address;
    private String url;
    private String email;
    private Boolean important;
    private String note;

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

    public String getEmail() {
        return email;
    }

    public void  setEmail(String email) {
        this.email = email;
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
}
