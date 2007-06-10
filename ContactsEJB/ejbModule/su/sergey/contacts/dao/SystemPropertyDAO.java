package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.SystemPropertyCreateInfo;
import su.sergey.contacts.dto.SystemPropertyData;
import su.sergey.contacts.dto.SystemPropertyHandle;
import su.sergey.contacts.dto.SystemPropertyUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class SystemPropertyDAO extends AbstractDAO {
    public SystemPropertyDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(SystemPropertyCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO properties (name, value, format, type, maker, note) VALUES (?, ?, ?, ?, ?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getName());
            setString(pstmt, index++, value.getValue());
            setString(pstmt, index++, value.getFormat());
            setString(pstmt, index++, value.getType());
            setString(pstmt, index++, value.getMaker());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public SystemPropertyData find(SystemPropertyHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT name, value, format, type, maker, note FROM properties WHERE name = ?";
        SystemPropertyData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, handle.getName());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new SystemPropertyData();
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

    public void update(SystemPropertyHandle handle, SystemPropertyUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE properties SET value = ?, format = ?, type = ?, maker = ?, note = ? WHERE name = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getValue());
            setString(pstmt, index++, value.getFormat());
            setString(pstmt, index++, value.getType());
            setString(pstmt, index++, value.getMaker());
            setString(pstmt, index++, value.getNote());
            setString(pstmt, index++, handle.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(SystemPropertyHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM properties WHERE name = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, handle.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void addOuts(SqlOutAccessor accessor) {
        accessor.addOut("name");
        accessor.addOut("value");
        accessor.addOut("format");
        accessor.addOut("type");
        accessor.addOut("maker");
        accessor.addOut("note");
    }

    public int populate(SystemPropertyData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setName(getString(rs, index++));
        value.setValue(getString(rs, index++));
        value.setFormat(getString(rs, index++));
        value.setType(getString(rs, index++));
        value.setMaker(getString(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }
}
