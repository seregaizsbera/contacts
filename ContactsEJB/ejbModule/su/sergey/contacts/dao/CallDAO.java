package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.CallCreateInfo;
import su.sergey.contacts.dto.CallData;
import su.sergey.contacts.dto.CallHandle;
import su.sergey.contacts.dto.CallUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class CallDAO extends AbstractDAO {
    private static CallDAO instance = null;

    private CallDAO() {}

    public CallDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(CallCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO calls (moment, direction, phone, place, type, quantity, price, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            int index = 1;
            setTimestamp(pstmt, index++, value.getMoment());
            setInt(pstmt, index++, value.getDirection());
            setString(pstmt, index++, value.getPhone());
            setString(pstmt, index++, value.getPlace());
            setInt(pstmt, index++, value.getType());
            setTime(pstmt, index++, value.getQuantity());
            setCurrency(pstmt, index++, value.getPrice());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
            return getCurrentId(conn, "calls", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public CallData find(CallHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, moment, direction, phone, place, type, quantity, price, note FROM calls WHERE id = ?";
        CallData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new CallData();
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

    public void update(CallHandle handle, CallUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE calls SET moment = ?, direction = ?, phone = ?, place = ?, type = ?, quantity = ?, price = ?, note = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setTimestamp(pstmt, index++, value.getMoment());
            setInt(pstmt, index++, value.getDirection());
            setString(pstmt, index++, value.getPhone());
            setString(pstmt, index++, value.getPlace());
            setInt(pstmt, index++, value.getType());
            setTime(pstmt, index++, value.getQuantity());
            setCurrency(pstmt, index++, value.getPrice());
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

    public void remove(CallHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM calls WHERE id = ?";
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
        accessor.addOut("moment");
        accessor.addOut("direction");
        accessor.addOut("phone");
        accessor.addOut("place");
        accessor.addOut("type");
        accessor.addOut("quantity");
        accessor.addOut("price");
        accessor.addOut("note");
    }

    public int populate(CallData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setMoment(getTimestamp(rs, index++));
        value.setDirection(getInt(rs, index++));
        value.setPhone(getString(rs, index++));
        value.setPlace(getString(rs, index++));
        value.setType(getInt(rs, index++));
        value.setQuantity(getTime(rs, index++));
        value.setPrice(getCurrency(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }

    public static CallDAO getInstance() {
        if (instance == null) {
            instance = new CallDAO();
        }
        return instance;
    }
}
