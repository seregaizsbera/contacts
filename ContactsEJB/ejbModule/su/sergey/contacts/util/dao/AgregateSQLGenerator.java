package su.sergey.contacts.util.dao;

/**
 * ������ ��� ����������� �������������� SQL ��������.
 * 
 * @author ������ ��������
 */
public class AgregateSQLGenerator extends AbstractSQLGenerator {
    /**
     * ��������� � ��������� ������� ������������� �������� COUNT.
     * 
     * @param table ��� ������� � ������� ��������� ������� ������������
     *              � ������������ ���������.
     * @param column ��� ������� ������������ � ������������� ���������.
     * @param distinct ��������� ����� �� ������������� ��� ����������
     *                 ��� ������ ���������.
     * @throws IllegalStateException
     */
    public void count(String table, String column, boolean distinct) {
        if (select.length() != 0) {
            throw new IllegalStateException();
        } else {
            select.append("COUNT(").append(distinct ? "DISTINCT " : "").append(table).append('.').append(column).append(")");
        }
    }
}
