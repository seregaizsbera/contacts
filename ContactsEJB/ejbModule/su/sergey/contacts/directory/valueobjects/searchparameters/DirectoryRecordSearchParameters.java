package su.sergey.contacts.directory.valueobjects.searchparameters;

import java.io.Serializable;
import java.util.Properties;

import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;

/**
 * Параметры для поиска элементов справочника.
 * @author Сергей Богданов
 * @version 1.0 
 */
public class DirectoryRecordSearchParameters implements Serializable {
    /**
     * Дескриптор заголовка справочника.
     */
    private DirectoryMetadataHandle directoryMetadataHandle;

    /**
     * Значения для поиска для различных полей (соответствуют колонкам)
     */
    private Properties parameters;

    /**
     * Создает объект.
     */
    public DirectoryRecordSearchParameters(DirectoryMetadataHandle directoryMetadataHandle, Properties parameters) {
        this.directoryMetadataHandle = directoryMetadataHandle;
        this.parameters = parameters;
    }

    /**
     * Возвращает дескриптор заголовка справочника.
     */
    public DirectoryMetadataHandle getDirectoryMetadataHandle() {
        return directoryMetadataHandle;
    }

    /**
     * Возвращает значения для поиска для различных полей.
     */
    public Properties getParameters() {
        return parameters;
    }
}
