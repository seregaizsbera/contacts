package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.ShnipCreateInfo;
import su.sergey.contacts.dto.ShnipData;
import su.sergey.contacts.dto.ShnipHandle;
import su.sergey.contacts.dto.ShnipUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class ShnipDAO extends AbstractDAO {
    private static ShnipDAO instance = null;

    private ShnipDAO() {}

    public ShnipDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(ShnipCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO shnip (person, graduate, form_letter, form_leader, description) VALUES (?, ?, ?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setDate(pstmt, index++, value.getGraduate());
            setString(pstmt, index++, value.getFormLetter());
            setInt(pstmt, index++, value.getFormLeader());
            setString(pstmt, index++, value.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public ShnipData find(ShnipHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, graduate, form_letter, form_leader, description FROM shnip WHERE person = ?";
        ShnipData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new ShnipData();
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

    public void update(ShnipHandle handle, ShnipUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE shnip SET graduate = ?, form_letter = ?, form_leader = ?, description = ? WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setDate(pstmt, index++, value.getGraduate());
            setString(pstmt, index++, value.getFormLetter());
            setInt(pstmt, index++, value.getFormLeader());
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

    public void remove(ShnipHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM shnip WHERE person = ?";
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
        accessor.addOut("graduate");
        accessor.addOut("form_letter");
        accessor.addOut("form_leader");
        accessor.addOut("description");
    }

    public int populate(ShnipData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setGraduate(getDate(rs, index++));
        value.setFormLetter(getString(rs, index++));
        value.setFormLeader(getInt(rs, index++));
        value.setDescription(getString(rs, index++));
        return index;
    }

    public static ShnipDAO getInstance() {
        if (instance == null) {
            instance = new ShnipDAO();
        }
        return instance;
    }
}