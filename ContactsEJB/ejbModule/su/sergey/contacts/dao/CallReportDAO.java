package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.CallReportCreateInfo;
import su.sergey.contacts.dto.CallReportData;
import su.sergey.contacts.dto.CallReportHandle;
import su.sergey.contacts.dto.CallReportUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class CallReportDAO extends AbstractDAO {
    private static CallReportDAO instance = null;

    private CallReportDAO() {}

    public CallReportDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(CallReportCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO call_reports (first_day, last_day, arrival_day, process_day, pure_period_price, note) VALUES (?, ?, ?, ?, ?, ?)");
            int index = 1;
            setDate(pstmt, index++, value.getFirstDay());
            setDate(pstmt, index++, value.getLastDay());
            setDate(pstmt, index++, value.getArrivalDay());
            setDate(pstmt, index++, value.getProcessDay());
            setCurrency(pstmt, index++, value.getPurePeriodPrice());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
            return getCurrentId(conn, "call_reports", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public CallReportData find(CallReportHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, first_day, last_day, arrival_day, process_day, pure_period_price, note FROM call_reports WHERE id = ?";
        CallReportData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new CallReportData();
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

    public void update(CallReportHandle handle, CallReportUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE call_reports SET first_day = ?, last_day = ?, arrival_day = ?, process_day = ?, pure_period_price = ?, note = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setDate(pstmt, index++, value.getFirstDay());
            setDate(pstmt, index++, value.getLastDay());
            setDate(pstmt, index++, value.getArrivalDay());
            setDate(pstmt, index++, value.getProcessDay());
            setCurrency(pstmt, index++, value.getPurePeriodPrice());
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

    public void remove(CallReportHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM call_reports WHERE id = ?";
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
        accessor.addOut("first_day");
        accessor.addOut("last_day");
        accessor.addOut("arrival_day");
        accessor.addOut("process_day");
        accessor.addOut("pure_period_price");
        accessor.addOut("note");
    }

    public int populate(CallReportData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setFirstDay(getDate(rs, index++));
        value.setLastDay(getDate(rs, index++));
        value.setArrivalDay(getDate(rs, index++));
        value.setProcessDay(getDate(rs, index++));
        value.setPurePeriodPrice(getCurrency(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }

    public static CallReportDAO getInstance() {
        if (instance == null) {
            instance = new CallReportDAO();
        }
        return instance;
    }
}
