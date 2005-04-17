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

    public Integer create(SupplyCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO supplies (name, short_name, parent_name, kind, address, url, inn, kpp, ogrn, metro, important, property_form, holding, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getName());
            setString(pstmt, index++, value.getShortName());
            setString(pstmt, index++, value.getParentName());
            setInt(pstmt, index++, value.getKind());
            setString(pstmt, index++, value.getAddress());
            setString(pstmt, index++, value.getUrl());
            setString(pstmt, index++, value.getInn());
            setString(pstmt, index++, value.getKpp());
            setString(pstmt, index++, value.getOgrn());
            setString(pstmt, index++, value.getMetro());
            setBoolean(pstmt, index++, value.getImportant());
            setString(pstmt, index++, value.getPropertyForm());
            setBoolean(pstmt, index++, value.getHolding());
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

    public SupplyData find(SupplyHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, name, short_name, parent_name, kind, address, url, inn, kpp, ogrn, metro, important, property_form, holding, note, insert_time, update_time FROM supplies WHERE id = ?";
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

    public void update(SupplyHandle handle, SupplyUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE supplies SET name = ?, short_name = ?, parent_name = ?, kind = ?, address = ?, url = ?, inn = ?, kpp = ?, ogrn = ?, metro = ?, important = ?, property_form = ?, holding = ?, note = ? WHERE id = ?";
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
            setString(pstmt, index++, value.getKpp());
            setString(pstmt, index++, value.getOgrn());
            setString(pstmt, index++, value.getMetro());
            setBoolean(pstmt, index++, value.getImportant());
            setString(pstmt, index++, value.getPropertyForm());
            setBoolean(pstmt, index++, value.getHolding());
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

    public void remove(SupplyHandle handle) {
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
        accessor.addOut("kpp");
        accessor.addOut("ogrn");
        accessor.addOut("metro");
        accessor.addOut("important");
        accessor.addOut("property_form");
        accessor.addOut("holding");
        accessor.addOut("note");
        accessor.addOut("insert_time");
        accessor.addOut("update_time");
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
        value.setKpp(getString(rs, index++));
        value.setOgrn(getString(rs, index++));
        value.setMetro(getString(rs, index++));
        value.setImportant(getBoolean(rs, index++));
        value.setPropertyForm(getString(rs, index++));
        value.setHolding(getBoolean(rs, index++));
        value.setNote(getString(rs, index++));
        value.setInsertTime(getTimestamp(rs, index++));
        value.setUpdateTime(getTimestamp(rs, index++));
        return index;
    }

    public static SupplyDAO getInstance() {
        if (instance == null) {
            instance = new SupplyDAO();
        }
        return instance;
    }
}
