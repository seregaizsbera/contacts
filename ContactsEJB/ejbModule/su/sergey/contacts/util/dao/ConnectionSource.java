package su.sergey.contacts.util.dao;

import java.sql.Connection;

/**
 * �������� ���������� AbstractDAO �� ���������
 * ������� ��������� ���������� � ����� ������.
 * 
 * @author ������ ��������
 */
public interface ConnectionSource {
    /**
     * ���������� ���������� � ����� ������. ������ ���������� �����
     * ������� ���������� � ������������ ������� ������ ���� ������������
     * ������� ������ void close(Connection conn) throws DAOException.
     * 
     * @return ���������� � ����� ������
     */
    Connection getConnection();
    
    /**
     * ���������� ���������� � ����� ������. ������ ���������� �����
     * ������� ���������� � ������������ ������� ������ ���� ������������
     * ������� ������ void close(Connection conn).
     * 
     * @param userName ��� ������������
     * @param password ������
     * @return ���������� � ����� ������
     */
    Connection getConnection(String userName, String password);
    
    /**
     * ������������ ������� ����������� ��� ����������.
     * @param conn ���������� � ����� ������ ���������� � ������� ������
     * Connection getConnection() throws DAOException.
     */
    void close(Connection conn);
}
