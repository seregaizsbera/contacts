package su.sergey.contacts.directory.businessdelegate.impl;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.JNDINamesForWeb;
import su.sergey.contacts.directory.DirectoryRecordsPageIterator;
import su.sergey.contacts.directory.DirectoryRecordsPageIteratorHome;
import su.sergey.contacts.directory.businessdelegate.DirectoryRecordsPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.dao.DAOException;

/**
 * Имплементация интерфейса <code>DirectoryRecordsPageIteratorBusinessDelegate</code>
 */
public class DefaultDirectoryRecordsPageIteratorBusinessDelegate
        implements DirectoryRecordsPageIteratorBusinessDelegate {
    private DirectoryRecordsPageIterator iterator;
    private DirectoryRecordSearchParameters searchParameters;
    private int pageSize;

    /**
     * Создаёт новый объект для записей справочника
     *
     * @param searchParameters параметры поиска
     * @param pageSize Число записей на страницу
     * */
    public DefaultDirectoryRecordsPageIteratorBusinessDelegate (DirectoryRecordSearchParameters searchParameters, int pageSize) {
        if (searchParameters == null) {
            throw new NullPointerException("Null directory search parameters");
        }
        this.searchParameters = searchParameters;
        this.pageSize = pageSize;
        this.iterator = null;
	    initIterator();
    }

	private void initIterator() {
		try {
		   Context ctx = new InitialContext();
		   Object homeObject = ctx.lookup(JNDINamesForWeb.DIRECTORY_RECORDS_PAGE_ITERATOR_REFERENCE);
		   DirectoryRecordsPageIteratorHome home = (DirectoryRecordsPageIteratorHome)
		           PortableRemoteObject.narrow(homeObject,
		                   DirectoryRecordsPageIteratorHome.class );
		   iterator = home.create(searchParameters, pageSize);
		} catch (NamingException e) {
		   e.printStackTrace();
		} catch (CreateException e) {
		   e.printStackTrace();
		} catch (RemoteException e) {
		   e.printStackTrace();
		}
	}
    
    public DirectoryRecord[] next() throws ContactsException {
        try {
            return iterator.next();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.next();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public DirectoryRecord[] current() throws ContactsException {
        try {
            return iterator.current();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.current();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public DirectoryRecord[] prev() throws ContactsException {
        try {
            return iterator.prev();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.prev();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public boolean hasNext() throws ContactsException {
        try {
            return iterator.hasNext();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.hasNext();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public boolean hasPrev() throws ContactsException {
        try {
            return iterator.hasPrev();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.hasPrev();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public int getCurrentPozition() throws ContactsException {
        try {
            return iterator.getCurrentPozition();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getCurrentPozition();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public int getCurrentPage() throws ContactsException {
        try {
            return iterator.getCurrentPage();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getCurrentPage();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public int getPageSize() throws ContactsException {
        try {
            return iterator.getPageSize();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getPageSize();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public int getNumberOfPages() throws ContactsException {
        try {
            return iterator.getTotalPageCount();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getTotalPageCount();
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }

    public DirectoryRecord[] goToPage(int number) throws ContactsException {
        try {
            return iterator.goTo(number);
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.goTo(number);
	        } catch (DAOException e1) {
	            throw new ContactsException(e);
            } catch (RemoteException e1) {
                throw new ContactsException(e1);
            }
        } catch (DAOException e) {
            throw new ContactsException(e);
        } catch (RemoteException e) {
            throw new ContactsException(e);
        }
    }
    
	/**
	 * @see DirectoryRecordsPageIteratorBusinessDelegate#freeResources()
	 */
	public void freeResources() {
        if (iterator != null) {
            try {
                iterator.remove();
            } catch (NoSuchObjectException e) {
            } catch (RemoveException e) {
            } catch (RemoteException e) {
            }
        }
        iterator = null;
	}
}
