package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.CallExpenseCreateInfo;
import su.sergey.contacts.dto.CallExpenseData;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.dto.CallExpenseUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class CallExpenseDAO extends AbstractDAO {
    private static CallExpenseDAO instance = null;

    private CallExpenseDAO() {}

    public CallExpenseDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(CallExpenseCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO call_expenses (report, kind, expense, price) VALUES (?, ?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getReport());
            setInt(pstmt, index++, value.getKind());
            setInt(pstmt, index++, value.getExpense());
            setCurrency(pstmt, index++, value.getPrice());
            pstmt.executeUpdate();
            return getCurrentId(conn, "call_expenses", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public CallExpenseData find(CallExpenseHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, report, kind, expense, price FROM call_expenses WHERE id = ?";
        CallExpenseData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new CallExpenseData();
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

    public void update(CallExpenseHandle handle, CallExpenseUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE call_expenses SET report = ?, kind = ?, expense = ?, price = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, value.getReport());
            setInt(pstmt, index++, value.getKind());
            setInt(pstmt, index++, value.getExpense());
            setCurrency(pstmt, index++, value.getPrice());
            setInt(pstmt, index++, handle.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(CallExpenseHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM call_expenses WHERE id = ?";
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
        accessor.addOut("report");
        accessor.addOut("kind");
        accessor.addOut("expense");
        accessor.addOut("price");
    }

    public int populate(CallExpenseData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setReport(getInt(rs, index++));
        value.setKind(getInt(rs, index++));
        value.setExpense(getInt(rs, index++));
        value.setPrice(getCurrency(rs, index++));
        return index;
    }

    public static CallExpenseDAO getInstance() {
        if (instance == null) {
            instance = new CallExpenseDAO();
        }
        return instance;
    }
}
