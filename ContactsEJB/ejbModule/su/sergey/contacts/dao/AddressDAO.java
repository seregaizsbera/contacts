package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.AddressCreateInfo;
import su.sergey.contacts.dto.AddressData;
import su.sergey.contacts.dto.AddressHandle;
import su.sergey.contacts.dto.AddressUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class AddressDAO extends AbstractDAO {
    public AddressDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(AddressCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO addresses (person, address) VALUES (?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setString(pstmt, index++, value.getAddress());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public AddressData find(AddressHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, address FROM addresses WHERE person = ?";
        AddressData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new AddressData();
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

    public void update(AddressHandle handle, AddressUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE addresses SET address = ? WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getAddress());
            setInt(pstmt, index++, handle.getPerson());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(AddressHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM addresses WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void addOuts(SqlOutAccessor accessor) {
        accessor.addOut("person");
        accessor.addOut("address");
    }

    public int populate(AddressData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setAddress(getString(rs, index++));
        return index;
    }
}
