package su.sergey.contacts.dto;

import java.io.Serializable;

public final class QueryData implements Serializable, QueryCreateInfo, QueryUpdateInfo {
    private Integer id;
    private String userName;
    private String sql;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void  setUserName(String userName) {
        this.userName = userName;
    }

    public String getSql() {
        return sql;
    }

    public void  setSql(String sql) {
        this.sql = sql;
    }
}
