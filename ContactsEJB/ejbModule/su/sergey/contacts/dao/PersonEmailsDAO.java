package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.PersonEmailsCreateInfo;
import su.sergey.contacts.dto.PersonEmailsData;
import su.sergey.contacts.dto.PersonEmailsHandle;
import su.sergey.contacts.dto.PersonEmailsUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class PersonEmailsDAO extends AbstractDAO {
    private static PersonEmailsDAO instance = null;

    private PersonEmailsDAO() {}

    public PersonEmailsDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(PersonEmailsCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO person_emails (person, email, basic) VALUES (?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setInt(pstmt, index++, value.getEmail());
            setBoolean(pstmt, index++, value.getBasic());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public PersonEmailsData find(PersonEmailsHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, email, basic FROM person_emails WHERE person = ? AND email = ?";
        PersonEmailsData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            setInt(pstmt, index++, handle.getEmail());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new PersonEmailsData();
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

    public void update(PersonEmailsHandle handle, PersonEmailsUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE person_emails SET basic = ? WHERE person = ? AND email = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setBoolean(pstmt, index++, value.getBasic());
            setInt(pstmt, index++, handle.getPerson());
            setInt(pstmt, index++, handle.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void remove(PersonEmailsHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM person_emails WHERE person = ? AND email = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            setInt(pstmt, index++, handle.getEmail());
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
        accessor.addOut("email");
        accessor.addOut("basic");
    }

    public int populate(PersonEmailsData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setEmail(getInt(rs, index++));
        value.setBasic(getBoolean(rs, index++));
        return index;
    }

    public static PersonEmailsDAO getInstance() {
        if (instance == null) {
            instance = new PersonEmailsDAO();
        }
        return instance;
    }
}
