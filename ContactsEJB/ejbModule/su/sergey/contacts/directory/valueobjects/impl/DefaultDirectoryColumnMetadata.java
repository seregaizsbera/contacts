package su.sergey.contacts.directory.valueobjects.impl;

import java.sql.Types;

import su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.util.ContactsDateTimeFormat;

/**
 * Дефалтовая имплементация интерфейса <tt>DirectoryColumnMetadata</tt>.
 * 
 * @author: Сергей Богданов
 */
public class DefaultDirectoryColumnMetadata implements DirectoryColumnMetadata {
    /**
     * Могут ли быть в колонке нули
     */
    private boolean isNullable;

    /**
     * Тип данных колонки
     */
    private int type;

    /**
     * Ширина колонки
     */
    private int width;

    /**
     * Имя колонки в базе данных (не показывается в UI).
     */
    private String dbColumnName;

    /**
     * Полное наименование. Выводится как заголовок для значения одного поля из одной записи справочника.
     */
    private String fullName;
    
    /**
     * Присваивается ли значение данному полю записи автоматически
     */
    private boolean isGenerated;    
    
    /**
     * Создает объект.
     */
    public DefaultDirectoryColumnMetadata() {}

    /**
     * Создает объект - для изменения объекта через пользовательский интерфейс.
     */
    public DefaultDirectoryColumnMetadata(String dbColumnName, String fullName, int width, int type, boolean isNullable, boolean isGenerated) {
        this.dbColumnName = dbColumnName;
        this.fullName = fullName;
        this.type = type;
        this.isNullable = isNullable;
        this.isGenerated = isGenerated;
        switch (type) {
        	case Types.SMALLINT:
        	case Types.INTEGER:
        	case Types.BIGINT:
        	    this.width = width * 3;
        	    break;
        	case Types.DATE:
        	    this.width = 10;
        	    break;
        	case Types.TIME:
        	    this.width = 8;
        	    break;
        	case Types.TIMESTAMP:
        	    this.width = 22;
        	    break;
        	default:
        	    this.width = width;
        }
    }

    /**
     * Возвращает имя колонки в базе данных.
     */
    public String getDbColumnName() {
        return dbColumnName;
    }

    /**
     * Возвращает полное наименование.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Возвращает ширину поля.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Возвращает тип данных поля
     */
    public int getType() {
        return type;
    }

    /**
     * Могут ли быть в колонке нули
     */
    public boolean isNullable() {
        return isNullable;
    }
    
	/**
	 * @see DirectoryColumnMetadata#isGenerated()
	 */
	public boolean isGenerated() {
		return isGenerated;
	}
	
	/**
	 * Sets the fullName
	 * @param fullName The fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
