package su.sergey.contacts.util.dao;

/**
 * Служит для составления простых SELECT запросов.
 * 
 * @author Сергей Богданов
 */
public class SQLGenerator extends AbstractSQLGenerator {
    public static final long ALL_RECORDS = -1;
    
    private long numberOfRecords;
    private long firstRecord;
    private boolean isForReadOnly;

    public void init(String table) {
        super.init(table);
        numberOfRecords = ALL_RECORDS;
        isForReadOnly = true;
        firstRecord = 1;
    }

    /**
     * Добавляет еще одну колонку в результат запроса.
     * 
     * @param table имя таблицы, колонку которой надо добавить к результат.
     * @param column имя колонки, которую надо добавить результат.
     */
    public void addOut(String table, String column) {
        if (select.length() != 0) {
            select.append(", ");
        }
        select.append(table).append('.').append(column);
    }

    /**
     * Добавляет еще одну колонку в результат запроса.
     * 
     * @param expression выражение, которое надо добавить к результат.
     */
    public void addOut(String expression) {
        if (select.length() != 0) {
            select.append(", ");
        }
        select.append(expression);
    }

    public boolean isForReadOnly() {
        return isForReadOnly;
    }

    public void setForReadOnly(boolean forReadOnly) {
        isForReadOnly = forReadOnly;
    }

    public String getSQL() {
    	String forReadOnly = isForReadOnly ? " for read only " : " ";
    	String limit = " limit "
    	               + ((numberOfRecords == ALL_RECORDS) ? "all" : ("" + numberOfRecords))
    	               + ", "
    	               + (firstRecord - 1);
    	String result = super.getSQL() + forReadOnly + limit;
    	return result;
    }
    
	/**
	 * Gets the numberOfRecords
	 * @return Returns a long
	 */
	public long getNumberOfRecords() {
		return numberOfRecords;
	}
	
	/**
	 * Sets the numberOfRecords
	 * @param numberOfRecords The numberOfRecords to set
	 */
	public void setNumberOfRecords(long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	/**
	 * Gets the firstRecord
	 * @return Returns a long
	 */
	public long getFirstRecord() {
		return firstRecord;
	}
	
	/**
	 * Sets the firstRecord
	 * @param firstRecord The firstRecord to set
	 */
	public void setFirstRecord(long firstRecord) {
		this.firstRecord = firstRecord;
	}
}
