package su.sergey.contacts.valueobjects.impl;

import su.sergey.contacts.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

/**
 * Дефалтовая имплементация интерфейса <code>DirectoryMetadata</code>.
 * @author: 
 * @version: 1.0
 */
public class DefaultDirectoryMetadata implements DirectoryMetadata {

    /**
     * Дескриптор метаданных справочника.
     */
    private DirectoryMetadataHandle handle;
    /**
     * Примечание к справочнику.
     */
    private String description;
    /**
     * Название схемы со справочником в базе данных.
     */
    private String dbSchemaName;
    /**
     * Название таблицы со справочником в базе данных.
     */
    private String dbTableName;
    /**
     * Список мета-элементов справочника.
     */
    private DirectoryColumnMetadata[] columnMetadata;

    /**
     * Создает объект.
     */
    public DefaultDirectoryMetadata() {}

    /**
     * Создает объект используя имя схемы, таблицы, описание, список колонок
     */
    public DefaultDirectoryMetadata(String dbSchemaName, String dbTableName, String description, DirectoryColumnMetadata[] columnMetadata) {
        this.handle = new DirectoryMetadataHandle(dbTableName);
        this.dbSchemaName = dbSchemaName;
        this.dbTableName = dbTableName;
        this.description = description;
        this.columnMetadata = columnMetadata;
    }

    /**
     * Возвращает дескриптор метаданных справочника.
     */
    public DirectoryMetadataHandle getHandle() {
        return handle;
    }

    /**
     * Возвращает примечание к справочнику.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает новое примечание к справочнику.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает название схемы со справочником в базе данных.
     */
    public String getDbSchemaName() {
        return dbSchemaName;
    }

    /**
     * Возвращает название таблицы со справочником в базе данных.
     */
    public String getDbTableName() {
        return dbTableName;
    }

    /**
     * Возвращает список мета-элементов справочника.
     */
    public DirectoryColumnMetadata[] getColumnMetadata() {
        return columnMetadata;
    }
}
