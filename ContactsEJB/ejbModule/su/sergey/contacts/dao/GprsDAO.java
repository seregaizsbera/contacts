package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.GprsCreateInfo;
import su.sergey.contacts.dto.GprsData;
import su.sergey.contacts.dto.GprsHandle;
import su.sergey.contacts.dto.GprsUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class GprsDAO extends AbstractDAO {
    private static GprsDAO instance = null;

    private GprsDAO() {}

    public GprsDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(GprsCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO gprs (moment, direction, url, traffic, price, note) VALUES (?, ?, ?, ?, ?, ?)");
            int index = 1;
            setTimestamp(pstmt, index++, value.getMoment());
            setInt(pstmt, index++, value.getDirection());
            setInt(pstmt, index++, value.getUrl());
            setInt(pstmt, index++, value.getTraffic());
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

    public GprsData find(GprsHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, moment, direction, url, traffic, price, note FROM gprs WHERE id = ?";
        GprsData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new GprsData();
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

    public void update(GprsHandle handle, GprsUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE gprs SET moment = ?, direction = ?, url = ?, traffic = ?, price = ?, note = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setTimestamp(pstmt, index++, value.getMoment());
            setInt(pstmt, index++, value.getDirection());
            setInt(pstmt, index++, value.getUrl());
            setInt(pstmt, index++, value.getTraffic());
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

    public void remove(GprsHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM gprs WHERE id = ?";
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
        accessor.addOut("url");
        accessor.addOut("traffic");
        accessor.addOut("price");
        accessor.addOut("note");
    }

    public int populate(GprsData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setMoment(getTimestamp(rs, index++));
        value.setDirection(getInt(rs, index++));
        value.setUrl(getInt(rs, index++));
        value.setTraffic(getInt(rs, index++));
        value.setPrice(getCurrency(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }

    public static GprsDAO getInstance() {
        if (instance == null) {
            instance = new GprsDAO();
        }
        return instance;
    }
}
