package su.sergey.contacts.valueobjects.impl;

import su.sergey.contacts.valueobjects.DirectoryColumnMetadata;

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
        this.width = width;
        this.type = type;
        this.isNullable = isNullable;
        this.isGenerated = isGenerated;
    }

    /**
     * Возвращает имя колонки в базе данных.
     */
    public String getDbColumnName() {
        return dbColumnName;
    }

    /**
     * Устанавливает имя колонки в базе данных.
     */
    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    /**
     * Возвращает полное наименование.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Устанавливает новое полное наименование.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
	 * @see DirectoryColumnMetadata#isGenerated()
	 */
	public void setGenerated(boolean isGenerated) {
		this.isGenerated = isGenerated;
	}
}
