package su.sergey.contacts.util.dao;

/**
 * Имплементация SqlOutAccessor которая работает с SQLGenerator:
 * SqlOutAccessor.addOut делегируется в вызов SQLGenerator.addOut
 * с добавление параметра &lt;имя таблицы&gt;, который берется из свойства
 * table этого класса.
 * 
 * @author 
 */
public class TableOutAccessor implements SqlOutAccessor {
    private String table;
    private SQLGenerator sqlGenerator;

    /**
     * Создает экземпляр этого класса.
     * 
     * @param table изначальное значение свойства table.
     * @param sqlGenerator экземпляр SQLGenerator которому
     *                     будут делегироваться вызовы addOut.
     */
    public TableOutAccessor(String table, SQLGenerator sqlGenerator) {
        this.table = table;
        this.sqlGenerator = sqlGenerator;
    }

    public void addOut(String columnName) {
        sqlGenerator.addOut(table, columnName);
    }

    /**
     * Вовзращает значение свойства table
     * 
     * @retun значение свойства table
     */
    public String getTable() {
        return table;
    }

    /**
     * Устанавливает значени свойства table.
     * 
     * @param table новое значение свойства table.
     */
    public void setTable(String table) {
        this.table = table;
    }
}
