package su.sergey.contacts.directory.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;

/**
 * Содержит описание структуры таблицы.
 * 
 * @author: Сергей Богданов
 */
public interface DirectoryMetadata extends Serializable {

    /**
     * Возвращает дескриптор справочника.
     */
    DirectoryMetadataHandle getHandle();
    
    /**
     * Возвращает примечание к справочнику.
     */
    String getDescription();

    /**
     * Устанавливает примечание к справочнику.
     */
    void setDescription(String description);
    
    /**
     * Возвращает название схемы со справочником в базе данных.
     */
    String getDbSchemaName();

    /**
     * Возвращает название таблицы со справочником в базе данных.
     */
    String getDbTableName();

    /**
     * Возвращает список мета-элементов справочника.
     */
    DirectoryColumnMetadata[] getColumnMetadata();
}
