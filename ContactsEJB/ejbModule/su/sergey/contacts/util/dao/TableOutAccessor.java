package su.sergey.contacts.util.dao;

/**
 * ������������� SqlOutAccessor ������� �������� � SQLGenerator:
 * SqlOutAccessor.addOut ������������ � ����� SQLGenerator.addOut
 * � ���������� ��������� &lt;��� �������&gt;, ������� ������� �� ��������
 * table ����� ������.
 * 
 * @author 
 */
public class TableOutAccessor implements SqlOutAccessor {
    private String table;
    private SQLGenerator sqlGenerator;

    /**
     * ������� ��������� ����� ������.
     * 
     * @param table ����������� �������� �������� table.
     * @param sqlGenerator ��������� SQLGenerator ��������
     *                     ����� �������������� ������ addOut.
     */
    public TableOutAccessor(String table, SQLGenerator sqlGenerator) {
        this.table = table;
        this.sqlGenerator = sqlGenerator;
    }

    public void addOut(String columnName) {
        sqlGenerator.addOut(table, columnName);
    }

    /**
     * ���������� �������� �������� table
     * 
     * @retun �������� �������� table
     */
    public String getTable() {
        return table;
    }

    /**
     * ������������� ������� �������� table.
     * 
     * @param table ����� �������� �������� table.
     */
    public void setTable(String table) {
        this.table = table;
    }
}
