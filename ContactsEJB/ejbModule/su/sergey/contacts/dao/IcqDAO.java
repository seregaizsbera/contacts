package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.IcqCreateInfo;
import su.sergey.contacts.dto.IcqData;
import su.sergey.contacts.dto.IcqHandle;
import su.sergey.contacts.dto.IcqUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class IcqDAO extends AbstractDAO {
    public IcqDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(IcqCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO icqs (person, icq, nickname) VALUES (?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setLong(pstmt, index++, value.getIcq());
            setString(pstmt, index++, value.getNickname());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public IcqData find(IcqHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, icq, nickname FROM icqs WHERE person = ?";
        IcqData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new IcqData();
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

    public void update(IcqHandle handle, IcqUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE icqs SET icq = ?, nickname = ? WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setLong(pstmt, index++, value.getIcq());
            setString(pstmt, index++, value.getNickname());
            setInt(pstmt, index++, handle.getPerson());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(IcqHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM icqs WHERE person = ?";
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
        accessor.addOut("icq");
        accessor.addOut("nickname");
    }

    public int populate(IcqData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setIcq(getLong(rs, index++));
        value.setNickname(getString(rs, index++));
        return index;
    }
}
