package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.InquiryCreateInfo;
import su.sergey.contacts.dto.InquiryData;
import su.sergey.contacts.dto.InquiryHandle;
import su.sergey.contacts.dto.InquiryUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class InquiryDAO extends AbstractDAO {
    public InquiryDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(InquiryCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO inquiry (alias, query, scope, mode, role, description) VALUES (?, ?, ?, ?, ?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getAlias());
            setString(pstmt, index++, value.getQuery());
            setInt(pstmt, index++, value.getScope());
            setInt(pstmt, index++, value.getMode());
            setString(pstmt, index++, value.getRole());
            setString(pstmt, index++, value.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public InquiryData find(InquiryHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT alias, query, scope, mode, role, description FROM inquiry WHERE alias = ?";
        InquiryData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, handle.getAlias());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new InquiryData();
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

    public void update(InquiryHandle handle, InquiryUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE inquiry SET query = ?, scope = ?, mode = ?, role = ?, description = ? WHERE alias = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getQuery());
            setInt(pstmt, index++, value.getScope());
            setInt(pstmt, index++, value.getMode());
            setString(pstmt, index++, value.getRole());
            setString(pstmt, index++, value.getDescription());
            setString(pstmt, index++, handle.getAlias());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(InquiryHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM inquiry WHERE alias = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, handle.getAlias());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void addOuts(SqlOutAccessor accessor) {
        accessor.addOut("alias");
        accessor.addOut("query");
        accessor.addOut("scope");
        accessor.addOut("mode");
        accessor.addOut("role");
        accessor.addOut("description");
    }

    public int populate(InquiryData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setAlias(getString(rs, index++));
        value.setQuery(getString(rs, index++));
        value.setScope(getInt(rs, index++));
        value.setMode(getInt(rs, index++));
        value.setRole(getString(rs, index++));
        value.setDescription(getString(rs, index++));
        return index;
    }
}
