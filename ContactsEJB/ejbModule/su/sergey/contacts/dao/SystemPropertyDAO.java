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
    private static SystemPropertyDAO instance = null;

    private SystemPropertyDAO() {}

    public SystemPropertyDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(SystemPropertyCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO properties (name, value, format, type, parser, description) VALUES (?, ?, ?, ?, ?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getName());
            setString(pstmt, index++, value.getValue());
            setString(pstmt, index++, value.getFormat());
            setString(pstmt, index++, value.getType());
            setString(pstmt, index++, value.getParser());
            setString(pstmt, index++, value.getDescription());
            pstmt.executeUpdate();
            return getCurrentId(conn, "properties", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public SystemPropertyData find(SystemPropertyHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, name, value, format, type, parser, description FROM properties WHERE id = ?";
        SystemPropertyData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
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

    public void update(SystemPropertyHandle handle, SystemPropertyUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE properties SET name = ?, value = ?, format = ?, type = ?, parser = ?, description = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getName());
            setString(pstmt, index++, value.getValue());
            setString(pstmt, index++, value.getFormat());
            setString(pstmt, index++, value.getType());
            setString(pstmt, index++, value.getParser());
            setString(pstmt, index++, value.getDescription());
            setInt(pstmt, index++, handle.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(SystemPropertyHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM properties WHERE id = ?";
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
        accessor.addOut("name");
        accessor.addOut("value");
        accessor.addOut("format");
        accessor.addOut("type");
        accessor.addOut("parser");
        accessor.addOut("description");
    }

    public int populate(SystemPropertyData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setName(getString(rs, index++));
        value.setValue(getString(rs, index++));
        value.setFormat(getString(rs, index++));
        value.setType(getString(rs, index++));
        value.setParser(getString(rs, index++));
        value.setDescription(getString(rs, index++));
        return index;
    }

    public static SystemPropertyDAO getInstance() {
        if (instance == null) {
            instance = new SystemPropertyDAO();
        }
        return instance;
    }
}
