package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.QueryCreateInfo;
import su.sergey.contacts.dto.QueryData;
import su.sergey.contacts.dto.QueryHandle;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class QueryDAO extends AbstractDAO {
    private static QueryDAO instance = null;

    private QueryDAO() {}

    public QueryDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(QueryCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO queries (user_name, sql) VALUES (?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getUserName());
            setString(pstmt, index++, value.getSql());
            pstmt.executeUpdate();
            return getCurrentId(conn, "queries", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public QueryData find(QueryHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, user_name, sql FROM queries WHERE user_name = ? AND sql = ?";
        QueryData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, handle.getUserName());
            setString(pstmt, index++, handle.getSql());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new QueryData();
                populate(result, rs, 1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return result;
    }

    public void remove(QueryHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM queries WHERE user_name = ? AND sql = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, handle.getUserName());
            setString(pstmt, index++, handle.getSql());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void addOuts(SqlOutAccessor accessor) {
        accessor.addOut("id");
        accessor.addOut("user_name");
        accessor.addOut("sql");
    }

    public int populate(QueryData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setUserName(getString(rs, index++));
        value.setSql(getString(rs, index++));
        return index;
    }

    public static QueryDAO getInstance() {
        if (instance == null) {
            instance = new QueryDAO();
        }
        return instance;
    }
}
