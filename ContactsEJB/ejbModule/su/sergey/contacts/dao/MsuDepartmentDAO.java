package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.MsuDepartmentCreateInfo;
import su.sergey.contacts.dto.MsuDepartmentData;
import su.sergey.contacts.dto.MsuDepartmentHandle;
import su.sergey.contacts.dto.MsuDepartmentUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class MsuDepartmentDAO extends AbstractDAO {
    private static MsuDepartmentDAO instance = null;

    private MsuDepartmentDAO() {}

    public MsuDepartmentDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(MsuDepartmentCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO msu_departments (short_name, department) VALUES (?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getShortName());
            setString(pstmt, index++, value.getDepartment());
            pstmt.executeUpdate();
            return getCurrentId(conn, "msu_departments", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public MsuDepartmentData find(MsuDepartmentHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, short_name, department FROM msu_departments WHERE id = ?";
        MsuDepartmentData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new MsuDepartmentData();
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

    public void update(MsuDepartmentHandle handle, MsuDepartmentUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE msu_departments SET short_name = ?, department = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getShortName());
            setString(pstmt, index++, value.getDepartment());
            setInt(pstmt, index++, handle.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(MsuDepartmentHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM msu_departments WHERE id = ?";
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
        accessor.addOut("short_name");
        accessor.addOut("department");
    }

    public int populate(MsuDepartmentData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setShortName(getString(rs, index++));
        value.setDepartment(getString(rs, index++));
        return index;
    }

    public static MsuDepartmentDAO getInstance() {
        if (instance == null) {
            instance = new MsuDepartmentDAO();
        }
        return instance;
    }
}
