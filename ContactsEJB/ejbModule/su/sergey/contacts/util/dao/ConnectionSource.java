package su.sergey.contacts.util.dao;

import java.sql.Connection;

/**
 * �������� ���������� AbstractDAO �� ���������
 * ������� ��������� ���������� � ����� ������.
 * @author 
 */
public interface ConnectionSource {
    /**
     * ���������� ���������� � ����� ������. ������ ���������� �����
     * ������� ���������� � ������������ ������� ������ ���� ������������
     * ������� ������ void close(Connection conn) throws DAOException.
     * @return ���������� � ����� ������
     * @throws DAOException � ������ ���� �������������� � ���� ������ ��
     * �������������� ���������.
     */
    Connection getConnection() throws DAOException;
    /**
     * ������������ ������� ����������� ��� ����������.
     * @param conn ���������� � ����� ������ ���������� � ������� ������
     * Connection getConnection() throws DAOException.
     * @throws DAOException � ������ ���� ��������� �������� � �������������
     * �������� ���������� ��� ����������.
     */
    void close(Connection conn) throws DAOException;
}
