package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.EmailCreateInfo;
import su.sergey.contacts.dto.EmailData;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.EmailUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class EmailDAO extends AbstractDAO {
    public EmailDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(EmailCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO emails (email) VALUES (?)");
            int index = 1;
            setString(pstmt, index++, value.getEmail());
            pstmt.executeUpdate();
            return getCurrentId(conn, "emails", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public EmailData find(EmailHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, email FROM emails WHERE id = ?";
        EmailData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new EmailData();
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

    public void update(EmailHandle handle, EmailUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE emails SET email = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getEmail());
            setInt(pstmt, index++, handle.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(EmailHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM emails WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
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
        accessor.addOut("email");
    }

    public int populate(EmailData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setEmail(getString(rs, index++));
        return index;
    }
}
