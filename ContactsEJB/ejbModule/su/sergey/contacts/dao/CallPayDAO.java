package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.CallPayCreateInfo;
import su.sergey.contacts.dto.CallPayData;
import su.sergey.contacts.dto.CallPayHandle;
import su.sergey.contacts.dto.CallPayUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class CallPayDAO extends AbstractDAO {
    private static CallPayDAO instance = null;

    private CallPayDAO() {}

    public CallPayDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(CallPayCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO calls_pays (pay_day, amount, course, amount_in_abstract_units, note) VALUES (?, ?, ?, ?, ?)");
            int index = 1;
            setDate(pstmt, index++, value.getPayDay());
            setCurrency(pstmt, index++, value.getAmount());
            setCurrency(pstmt, index++, value.getCourse());
            setCurrency(pstmt, index++, value.getAmountInAbstractUnits());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
            return getCurrentId(conn, "calls_pays", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public CallPayData find(CallPayHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, pay_day, amount, course, amount_in_abstract_units, note FROM calls_pays WHERE id = ?";
        CallPayData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new CallPayData();
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

    public void update(CallPayHandle handle, CallPayUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE calls_pays SET pay_day = ?, amount = ?, course = ?, amount_in_abstract_units = ?, note = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setDate(pstmt, index++, value.getPayDay());
            setCurrency(pstmt, index++, value.getAmount());
            setCurrency(pstmt, index++, value.getCourse());
            setCurrency(pstmt, index++, value.getAmountInAbstractUnits());
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

    public void remove(CallPayHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM calls_pays WHERE id = ?";
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
        accessor.addOut("pay_day");
        accessor.addOut("amount");
        accessor.addOut("course");
        accessor.addOut("amount_in_abstract_units");
        accessor.addOut("note");
    }

    public int populate(CallPayData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setPayDay(getDate(rs, index++));
        value.setAmount(getCurrency(rs, index++));
        value.setCourse(getCurrency(rs, index++));
        value.setAmountInAbstractUnits(getCurrency(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }

    public static CallPayDAO getInstance() {
        if (instance == null) {
            instance = new CallPayDAO();
        }
        return instance;
    }
}
