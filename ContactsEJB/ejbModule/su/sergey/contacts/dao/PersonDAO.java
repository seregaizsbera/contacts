package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.PersonCreateInfo;
import su.sergey.contacts.dto.PersonData;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PersonUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class PersonDAO extends AbstractDAO {
    private static PersonDAO instance = null;

    private PersonDAO() {}

    public PersonDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer create(PersonCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO persons (first, middle, last, note) VALUES (?, ?, ?, ?)");
            int index = 1;
            setString(pstmt, index++, value.getFirst());
            setString(pstmt, index++, value.getMiddle());
            setString(pstmt, index++, value.getLast());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
            return getCurrentId(conn, "persons", "id");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public PersonData find(PersonHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT id, first, middle, last, note FROM persons WHERE id = ?";
        PersonData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new PersonData();
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

    public void update(PersonHandle handle, PersonUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE persons SET first = ?, middle = ?, last = ?, note = ? WHERE id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getFirst());
            setString(pstmt, index++, value.getMiddle());
            setString(pstmt, index++, value.getLast());
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

    public void remove(PersonHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM persons WHERE id = ?";
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
        accessor.addOut("first");
        accessor.addOut("middle");
        accessor.addOut("last");
        accessor.addOut("note");
    }

    public int populate(PersonData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setId(getInt(rs, index++));
        value.setFirst(getString(rs, index++));
        value.setMiddle(getString(rs, index++));
        value.setLast(getString(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }

    public static PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }
}
