package su.sergey.contacts.directory.wrappers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.businessdelegate.PageIteratorBusinessDelegate;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;

class DirectoryHttpSession implements DirectoryDefinitions {
    private HttpSession session;

    public DirectoryHttpSession(HttpSession session) {
        this.session = session;
    }

    /**
     * Устанавливает в сессии параметры поиска по записям таблицы
     */
    public void setDirectoryRecordSearchParameters(DirectoryRecordSearchParameters searchParameters) {
    	session.setAttribute(SESSION_DIRECTORY_RECORDS_SEARCH_PARAMETERS, searchParameters);
    }
    
    /**
     * Удаляет из сессии параметры поиска по записям таблицы
     */
    public void removeDirectoryRecordSearchParameters() {
    	session.removeAttribute(SESSION_DIRECTORY_RECORDS_SEARCH_PARAMETERS);
    }
    
    /**
     * Устанавливает page iterator в сессии
     */
    public void setPageIterator(PageIteratorBusinessDelegate iterator, String iteratorName) {
        session.setAttribute(iteratorName, iterator);
    }

    /**
     * Удаляет page iterator из сессии
     */
    public void removePageIterator(String iteratorName) {
        session.removeAttribute(iteratorName);
    }

    /**
     * Берет page iterator из сессии
     */
    public PageIteratorBusinessDelegate getPageIterator(String iteratorName) {
        PageIteratorBusinessDelegate iterator =
                (PageIteratorBusinessDelegate)session.getAttribute(iteratorName);
        return iterator;
    }

    /**
     * Берет метаданные директории из сессии
     */
    public DirectoryMetadata getDirectoryMetadata() {
        return (DirectoryMetadata)session.getAttribute(SESSION_DIRECTORY_META_DATA);
    }

    /**
     * Устанавливает метаданные директории в сессии
     */
    public void setDirectoryMetadata(DirectoryMetadata directoryMetadata) {
        session.setAttribute(SESSION_DIRECTORY_META_DATA, directoryMetadata);
    }
}
