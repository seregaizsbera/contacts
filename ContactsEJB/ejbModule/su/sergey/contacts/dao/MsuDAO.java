package su.sergey.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.dto.MsuCreateInfo;
import su.sergey.contacts.dto.MsuData;
import su.sergey.contacts.dto.MsuHandle;
import su.sergey.contacts.dto.MsuUpdateInfo;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SqlOutAccessor;

public final class MsuDAO extends AbstractDAO {
    private static MsuDAO instance = null;

    private MsuDAO() {}

    public MsuDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public void create(MsuCreateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO msu (person, graduate, department, hospice, tutor, subfaculty, note) VALUES (?, ?, ?, ?, ?, ?, ?)");
            int index = 1;
            setInt(pstmt, index++, value.getPerson());
            setDate(pstmt, index++, value.getGraduate());
            setInt(pstmt, index++, value.getDepartment());
            setBoolean(pstmt, index++, value.getHospice());
            setBoolean(pstmt, index++, value.getTutor());
            setString(pstmt, index++, value.getSubfaculty());
            setString(pstmt, index++, value.getNote());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public MsuData find(MsuHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT person, graduate, department, hospice, tutor, subfaculty, note FROM msu WHERE person = ?";
        MsuData result = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setInt(pstmt, index++, handle.getPerson());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new MsuData();
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

    public void update(MsuHandle handle, MsuUpdateInfo value) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE msu SET graduate = ?, department = ?, hospice = ?, tutor = ?, subfaculty = ?, note = ? WHERE person = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int index = 1;
            setDate(pstmt, index++, value.getGraduate());
            setInt(pstmt, index++, value.getDepartment());
            setBoolean(pstmt, index++, value.getHospice());
            setBoolean(pstmt, index++, value.getTutor());
            setString(pstmt, index++, value.getSubfaculty());
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

    public void remove(MsuHandle handle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM msu WHERE person = ?";
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
        accessor.addOut("department");
        accessor.addOut("hospice");
        accessor.addOut("tutor");
        accessor.addOut("subfaculty");
        accessor.addOut("note");
    }

    public int populate(MsuData value, ResultSet rs, int startIndex) throws SQLException {
        int index = startIndex;
        value.setPerson(getInt(rs, index++));
        value.setGraduate(getDate(rs, index++));
        value.setDepartment(getInt(rs, index++));
        value.setHospice(getBoolean(rs, index++));
        value.setTutor(getBoolean(rs, index++));
        value.setSubfaculty(getString(rs, index++));
        value.setNote(getString(rs, index++));
        return index;
    }

    public static MsuDAO getInstance() {
        if (instance == null) {
            instance = new MsuDAO();
        }
        return instance;
    }
}
