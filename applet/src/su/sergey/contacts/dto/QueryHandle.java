package su.sergey.contacts.dto;

import java.io.Serializable;

public final class QueryHandle implements Serializable {
    private String userName;
    private String sql;

    public QueryHandle(String userName, String sql) {
        this.userName = userName;
        this.sql = sql;
    }

    public String getUserName() {
        return userName;
    }

    public String getSql() {
        return sql;
    }
}
