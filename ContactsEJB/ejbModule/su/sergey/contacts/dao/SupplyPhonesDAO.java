package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.SupplyPhonesCreateInfo;
import su.sergey.contacts.dto.SupplyPhonesData;
import su.sergey.contacts.dto.SupplyPhonesHandle;
import su.sergey.contacts.dto.SupplyPhonesUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class SupplyPhonesDAO extends AbstractDAO {
    private static SupplyPhonesDAO instance = null;

    private SupplyPhonesDAO() {}

    public SupplyPhonesDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(SupplyPhonesCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO supply_phones (supply, phone) VALUES (?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getSupply());
            setInt(pstmt, index++, value.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public SupplyPhonesData find(SupplyPhonesHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT supply, phone FROM supply_phones WHERE supply = ? AND phone = ?";
        SupplyPhonesData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getSupply());
            setInt(pstmt, index++, handle.getPhone());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new SupplyPhonesData();
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

    public void update(SupplyPhonesHandle handle, SupplyPhonesUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE supply_phones SET  WHERE supply = ? AND phone = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getSupply());
            setInt(pstmt, index++, handle.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(SupplyPhonesHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM supply_phones WHERE supply = ? AND phone = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getSupply());
            setInt(pstmt, index++, handle.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void addOuts(SqlOutAccessor accessor) {
        accessor.addOut("supply");
        accessor.addOut("phone");
    }

    public int populate(SupplyPhonesData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setSupply(getInt(rs, index++));
        value.setPhone(getInt(rs, index++));
        return index;
    }

    public static SupplyPhonesDAO getInstance() {
        if (instance == null) {
            instance = new SupplyPhonesDAO();
        }
        return instance;
    }
}
