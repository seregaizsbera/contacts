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
    public CoworkerDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(CoworkerCreateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO coworkers (person, job, administration, department, post, note) VALUES (?, ?, ?, ?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setString(pstmt, index++, value.getJob());
            setString(pstmt, index++, value.getAdministration());
            setString(pstmt, index++, value.getDepartment());
            setString(pstmt, index++, value.getPost());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public CoworkerData find(CoworkerHandle handle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, job, administration, department, post, note FROM coworkers WHERE person = ?";
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

    public void update(CoworkerHandle handle, CoworkerUpdateInfo value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE coworkers SET job = ?, administration = ?, department = ?, post = ?, note = ? WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setString(pstmt, index++, value.getJob());
            setString(pstmt, index++, value.getAdministration());
            setString(pstmt, index++, value.getDepartment());
            setString(pstmt, index++, value.getPost());
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

    public void remove(CoworkerHandle handle) {
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
        accessor.addOut("administration");
        accessor.addOut("department");
        accessor.addOut("post");
        accessor.addOut("note");
    }

    public int populate(CoworkerData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setJob(getString(rs, index++));
        value.setAdministration(getString(rs, index++));
        value.setDepartment(getString(rs, index++));
        value.setPost(getString(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }
}
