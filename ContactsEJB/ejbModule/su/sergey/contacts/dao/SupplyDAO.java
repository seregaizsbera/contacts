package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.SupplyCreateInfo;
import su.sergey.contacts.dto.SupplyData;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.dto.SupplyUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class SupplyDAO extends AbstractDAO {
    private static SupplyDAO instance = null;

    private SupplyDAO() {}

    public SupplyDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(SupplyCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO supplies (name, short_name, parent_name, kind, address, url, inn, important, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getName());
            setString(pstmt, index++, value.getShortName());
            setString(pstmt, index++, value.getParentName());
            setInt(pstmt, index++, value.getKind());
            setString(pstmt, index++, value.getAddress());
            setString(pstmt, index++, value.getUrl());
            setString(pstmt, index++, value.getInn());
            setBoolean(pstmt, index++, value.getImportant());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
            return getCurrentId(conn, "supplies", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public SupplyData find(SupplyHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, name, short_name, parent_name, kind, address, url, inn, important, note FROM supplies WHERE id = ?";
        SupplyData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new SupplyData();
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

    public void update(SupplyHandle handle, SupplyUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE supplies SET name = ?, short_name = ?, parent_name = ?, kind = ?, address = ?, url = ?, inn = ?, important = ?, note = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getName());
            setString(pstmt, index++, value.getShortName());
            setString(pstmt, index++, value.getParentName());
            setInt(pstmt, index++, value.getKind());
            setString(pstmt, index++, value.getAddress());
            setString(pstmt, index++, value.getUrl());
            setString(pstmt, index++, value.getInn());
            setBoolean(pstmt, index++, value.getImportant());
            setString(pstmt, index++, value.getNote());
            setInt(pstmt, index++, handle.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(SupplyHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM supplies WHERE id = ?";
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
        accessor.addOut("short_name");
        accessor.addOut("parent_name");
        accessor.addOut("kind");
        accessor.addOut("address");
        accessor.addOut("url");
        accessor.addOut("inn");
        accessor.addOut("important");
        accessor.addOut("note");
    }

    public int populate(SupplyData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setName(getString(rs, index++));
        value.setShortName(getString(rs, index++));
        value.setParentName(getString(rs, index++));
        value.setKind(getInt(rs, index++));
        value.setAddress(getString(rs, index++));
        value.setUrl(getString(rs, index++));
        value.setInn(getString(rs, index++));
        value.setImportant(getBoolean(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }

    public static SupplyDAO getInstance() {
        if (instance == null) {
            instance = new SupplyDAO();
        }
        return instance;
    }
}
