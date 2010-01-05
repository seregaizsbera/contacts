package su.sergey.contacts.util.dao;

/**
 * �������� �������� ��������� ��� �������� ����������
 * ������� � ��������� ������� �� ��������������� DAO.
 * ������ ��� ������������ SQLGenerator.
 * 
 * @author ������ ��������
 */
public interface SqlOutAccessor {
    /**
     * ��������� ������� � ������.
     * @param columnName ��� �������.
     */
    void addOut(String columnName);
}
