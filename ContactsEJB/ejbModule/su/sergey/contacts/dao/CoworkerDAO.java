package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.CoworkerCreateInfo;
import su.sergey.contacts.dto.CoworkerData;
import su.sergey.contacts.dto.CoworkerHandle;
import su.sergey.contacts.dto.CoworkerUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class CoworkerDAO extends AbstractDAO {
    private static CoworkerDAO instance = null;

    private CoworkerDAO() {}

    public CoworkerDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(CoworkerCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO coworkers (person, job, note) VALUES (?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setString(pstmt, index++, value.getJob());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public CoworkerData find(CoworkerHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, job, note FROM coworkers WHERE person = ?";
        CoworkerData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new CoworkerData();
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

    public void update(CoworkerHandle handle, CoworkerUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE coworkers SET job = ?, note = ? WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getJob());
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

    public void remove(CoworkerHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM coworkers WHERE person = ?";
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
        accessor.addOut("job");
        accessor.addOut("note");
    }

    public int populate(CoworkerData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setJob(getString(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }

    public static CoworkerDAO getInstance() {
        if (instance == null) {
            instance = new CoworkerDAO();
        }
        return instance;
    }
}
