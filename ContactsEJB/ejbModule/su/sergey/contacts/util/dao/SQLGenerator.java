package su.sergey.contacts.util.dao;

/**
 * ������ ��� ����������� ������� SELECT ��������.
 * 
 * @author 
 */
public class SQLGenerator extends AbstractSQLGenerator {
    /**
     * ���� �������� maxFetch ����� ������ ����� ����� ��������
     * �� ������ ����� ���������� ��� ����������.
     */
    public static final int FETCH_ALL = -1;

    private int maxFetch = FETCH_ALL;
    private boolean isForReadOnly = true;

    public void init(String table) {
        super.init(table);
        maxFetch = FETCH_ALL;
    }

    /**
     * ��������� ��� ���� ������� � ��������� �������.
     * 
     * @param table ��� �������, ������� ������� ���� �������� � ���������.
     * @param column ��� �������, ������� ���� �������� ���������.
     */
    public void addOut(String table, String column) {
        if (select.length() != 0) {
            select.append(", ");
        }
        select.append(table).append('.').append(column);
    }

    /**
     * ������������� �������� ������� ���������� ������������
     * ����������� �����, ������� ����� ��������� � ���������� ����������
     * ����������� �������. ���������� ��� �������� ������ FETCH_ALL
     * ���� ������ �������� ��� ����������.
     * 
     * @param maxFetch ������������
     *                 ����������� �����, ������� ����� ��������� � ���������� ����������
     *                 ����������� �������.
     */
    public void setMaxFetch(int maxFetch) {
        this.maxFetch = maxFetch;
    }

    /**
     * @return ���������� ������������
     * ����������� �����, ������� ����� ��������� � ���������� ����������
     * ����������� �������. ���� ������������ ������������� ��������
     * �� ������ ������ ��� ����������.
     */
    public int getMaxFetch() {
        return maxFetch;
    }

    public boolean isForReadOnly() {
        return isForReadOnly;
    }

    public void setForReadOnly(boolean forReadOnly) {
        isForReadOnly = forReadOnly;
    }

    public String getSQL() {
        if (isForReadOnly) {
            return maxFetch <= FETCH_ALL ? (super.getSQL() + " FOR READ ONLY") : (super.getSQL() + " FETCH FIRST " + maxFetch + " ROWS ONLY FOR READ ONLY");
        } else {
            return maxFetch <= FETCH_ALL ? super.getSQL() : (super.getSQL() + " FETCH FIRST " + maxFetch + " ROWS ONLY");
        }
    }
}
