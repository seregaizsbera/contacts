package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.RelativeCreateInfo;
import su.sergey.contacts.dto.RelativeData;
import su.sergey.contacts.dto.RelativeHandle;
import su.sergey.contacts.dto.RelativeUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class RelativeDAO extends AbstractDAO {
    private static RelativeDAO instance = null;

    private RelativeDAO() {}

    public RelativeDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(RelativeCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO relatives (person, relationship, description) VALUES (?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setString(pstmt, index++, value.getRelationship());
            setString(pstmt, index++, value.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public RelativeData find(RelativeHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, relationship, description FROM relatives WHERE person = ?";
        RelativeData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new RelativeData();
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

    public void update(RelativeHandle handle, RelativeUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE relatives SET relationship = ?, description = ? WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getRelationship());
            setString(pstmt, index++, value.getDescription());
            setInt(pstmt, index++, handle.getPerson());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(RelativeHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM relatives WHERE person = ?";
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
        accessor.addOut("relationship");
        accessor.addOut("description");
    }

    public int populate(RelativeData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setRelationship(getString(rs, index++));
        value.setDescription(getString(rs, index++));
        return index;
    }

    public static RelativeDAO getInstance() {
        if (instance == null) {
            instance = new RelativeDAO();
        }
        return instance;
    }
}
