package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.SupplyEmailsCreateInfo;
import su.sergey.contacts.dto.SupplyEmailsData;
import su.sergey.contacts.dto.SupplyEmailsHandle;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class SupplyEmailsDAO extends AbstractDAO {
    public SupplyEmailsDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(SupplyEmailsCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO supply_emails (supply, email) VALUES (?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getSupply());
            setInt(pstmt, index++, value.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public SupplyEmailsData find(SupplyEmailsHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT supply, email FROM supply_emails WHERE supply = ? AND email = ?";
        SupplyEmailsData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getSupply());
            setInt(pstmt, index++, handle.getEmail());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new SupplyEmailsData();
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

    public void remove(SupplyEmailsHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM supply_emails WHERE supply = ? AND email = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getSupply());
            setInt(pstmt, index++, handle.getEmail());
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
        accessor.addOut("email");
    }

    public int populate(SupplyEmailsData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setSupply(getInt(rs, index++));
        value.setEmail(getInt(rs, index++));
        return index;
    }
}
