package su.sergey.contacts.valueobjects.handles;

import java.io.Serializable;

/**
 * Дескриптор заголовка таблицы.
 * 
 * @author Сергей Богданов
 */
public class DirectoryMetadataHandle implements Serializable {
    /**
     * Имя таблицы.
     */
    private String tableName;

    /**
     * Создает объект.
     */
    public DirectoryMetadataHandle(String tableName) {
        this.tableName = tableName;
    }

	/**
	 * Gets the tableName
	 * 
	 * @return Returns a String
	 */
	public String getTableName() {
		return tableName;
	}
}
