package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.PhoneCreateInfo;
import su.sergey.contacts.dto.PhoneData;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.PhoneUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class PhoneDAO extends AbstractDAO {
    private static PhoneDAO instance = null;

    private PhoneDAO() {}

    public PhoneDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(PhoneCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO phones (phone, type) VALUES (?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getPhone());
            setInt(pstmt, index++, value.getType());
            pstmt.executeUpdate();
            return getCurrentId(conn, "phones", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public PhoneData find(PhoneHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, phone, type FROM phones WHERE id = ?";
        PhoneData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new PhoneData();
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

    public void update(PhoneHandle handle, PhoneUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE phones SET phone = ?, type = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getPhone());
            setInt(pstmt, index++, value.getType());
            setInt(pstmt, index++, handle.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(PhoneHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM phones WHERE id = ?";
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
        accessor.addOut("phone");
        accessor.addOut("type");
    }

    public int populate(PhoneData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setPhone(getString(rs, index++));
        value.setType(getInt(rs, index++));
        return index;
    }

    public static PhoneDAO getInstance() {
        if (instance == null) {
            instance = new PhoneDAO();
        }
        return instance;
    }
}
