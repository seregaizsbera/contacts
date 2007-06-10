package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.FriendCreateInfo;
import su.sergey.contacts.dto.FriendData;
import su.sergey.contacts.dto.FriendHandle;
import su.sergey.contacts.dto.FriendUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class FriendDAO extends AbstractDAO {
    public FriendDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(FriendCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO friends (person, note) VALUES (?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public FriendData find(FriendHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, note FROM friends WHERE person = ?";
        FriendData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new FriendData();
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

    public void update(FriendHandle handle, FriendUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE friends SET note = ? WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getNote());
            setInt(pstmt, index++, handle.getPerson());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(FriendHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM friends WHERE person = ?";
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
        accessor.addOut("note");
    }

    public int populate(FriendData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }
}
