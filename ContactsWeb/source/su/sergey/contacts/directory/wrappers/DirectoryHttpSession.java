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
     * ������������� � ������ ��������� ������ �� ������� �������
     */
    public void setDirectoryRecordSearchParameters(DirectoryRecordSearchParameters searchParameters) {
    	session.setAttribute(SESSION_DIRECTORY_RECORDS_SEARCH_PARAMETERS, searchParameters);
    }
    
    /**
     * ������� �� ������ ��������� ������ �� ������� �������
     */
    public void removeDirectoryRecordSearchParameters() {
    	session.removeAttribute(SESSION_DIRECTORY_RECORDS_SEARCH_PARAMETERS);
    }
    
    /**
     * ������������� page iterator � ������
     */
    public void setPageIterator(PageIteratorBusinessDelegate iterator, String iteratorName) {
        session.setAttribute(iteratorName, iterator);
    }

    /**
     * ������� page iterator �� ������
     */
    public void removePageIterator(String iteratorName) {
        session.removeAttribute(iteratorName);
    }

    /**
     * ����� page iterator �� ������
     */
    public PageIteratorBusinessDelegate getPageIterator(String iteratorName) {
        PageIteratorBusinessDelegate iterator =
                (PageIteratorBusinessDelegate)session.getAttribute(iteratorName);
        return iterator;
    }

    /**
     * ����� ���������� ���������� �� ������
     */
    public DirectoryMetadata getDirectoryMetadata() {
        return (DirectoryMetadata)session.getAttribute(SESSION_DIRECTORY_META_DATA);
    }

    /**
     * ������������� ���������� ���������� � ������
     */
    public void setDirectoryMetadata(DirectoryMetadata directoryMetadata) {
        session.setAttribute(SESSION_DIRECTORY_META_DATA, directoryMetadata);
    }
}
