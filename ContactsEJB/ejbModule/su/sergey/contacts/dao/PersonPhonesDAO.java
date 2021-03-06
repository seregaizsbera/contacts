package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.PersonPhonesCreateInfo;
import su.sergey.contacts.dto.PersonPhonesData;
import su.sergey.contacts.dto.PersonPhonesHandle;
import su.sergey.contacts.dto.PersonPhonesUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class PersonPhonesDAO extends AbstractDAO {
    public PersonPhonesDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(PersonPhonesCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO person_phones (person, phone, basic) VALUES (?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setInt(pstmt, index++, value.getPhone());
            setBoolean(pstmt, index++, value.getBasic());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public PersonPhonesData find(PersonPhonesHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, phone, basic FROM person_phones WHERE person = ? AND phone = ?";
        PersonPhonesData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            setInt(pstmt, index++, handle.getPhone());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new PersonPhonesData();
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

    public void update(PersonPhonesHandle handle, PersonPhonesUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE person_phones SET basic = ? WHERE person = ? AND phone = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setBoolean(pstmt, index++, value.getBasic());
            setInt(pstmt, index++, handle.getPerson());
            setInt(pstmt, index++, handle.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(PersonPhonesHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM person_phones WHERE person = ? AND phone = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            setInt(pstmt, index++, handle.getPhone());
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
        accessor.addOut("phone");
        accessor.addOut("basic");
    }

    public int populate(PersonPhonesData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setPhone(getInt(rs, index++));
        value.setBasic(getBoolean(rs, index++));
        return index;
    }
}
