package su.sergey.contacts.directory.wrappers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.businessdelegate.PageIteratorBusinessDelegate;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.valueobjects.DirectoryMetadata;

class DirectoryHttpSession implements DirectoryDefinitions {
    private HttpSession session;

    public DirectoryHttpSession(HttpSession session) {
        this.session = session;
    }

    /**
     * ������������� page iterator � ������
     */
    public void setPageIterator(PageIteratorBusinessDelegate iterator, String iteratorName) throws ServletException {
        session.setAttribute(iteratorName, iterator);
    }

    /**
     * ����� page iterator �� ������
     */
    public PageIteratorBusinessDelegate getPageIterator(String iteratorName) throws ServletException {
        PageIteratorBusinessDelegate iterator =
                (PageIteratorBusinessDelegate)session.getAttribute(iteratorName);
        if (iterator == null) {
            throw new ServletException("No Clients iterator in session");
        }
        return iterator;
    }

    /**
     * ����� ���������� ���������� �� ������
     */
    public DirectoryMetadata getDirectoryMetadata() throws ServletException {
        return (DirectoryMetadata)session.getAttribute(SESSION_DIRECTORY_META_DATA);
    }

    /**
     * ������������� ���������� ���������� � ������
     */
    public void setDirectoryMetadata(DirectoryMetadata directoryMetadata)
            throws ServletException {
        session.setAttribute(SESSION_DIRECTORY_META_DATA, directoryMetadata);
    }


}
