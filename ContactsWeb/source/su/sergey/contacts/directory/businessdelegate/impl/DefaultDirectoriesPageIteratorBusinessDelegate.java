package su.sergey.contacts.directory.businessdelegate.impl;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.directory.DirectoriesPageIterator;
import su.sergey.contacts.directory.DirectoriesPageIteratorHome;
import su.sergey.contacts.directory.businessdelegate.DirectoriesPageIteratorBusinessDelegate;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.valueobjects.DirectoryMetadata;

/**
 * Имплементация интерфейса <code>IDirectoryMetadatasPageIteratorBusinessDelegate</code>
 */
public class DefaultDirectoriesPageIteratorBusinessDelegate
        implements DirectoriesPageIteratorBusinessDelegate {

    private DirectoriesPageIterator iterator;
    private int pageSize;

    /**
     * Создаёт новый объект для записей таблицы
     *
     * @param searchParameters параметры поиска
     * @param pageSize Число записей на страницу
     * */
    public DefaultDirectoriesPageIteratorBusinessDelegate(int pageSize) {
    	this.pageSize = pageSize;
		initIterator();
    }

	private void initIterator() {
		Context ctx;
		DirectoriesPageIteratorHome home;
		try {
		   ctx = new InitialContext();
		   Object homeObject = ctx.lookup(JNDINames.DIRECTORIES_PAGE_ITERATOR_REFERENCE);
		   home = (DirectoriesPageIteratorHome)
		           PortableRemoteObject.narrow(homeObject,
		                   DirectoriesPageIteratorHome.class );
		   iterator = home.create(pageSize);
		} catch (NamingException e) {
		   e.printStackTrace();
		} catch (CreateException e) {
		   e.printStackTrace();
		} catch (RemoteException e) {
		   e.printStackTrace();
		}
	}

    public DirectoryMetadata[] next() throws ContactsException {
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

    public DirectoryMetadata[] current() throws ContactsException {
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

    public DirectoryMetadata[] prev() throws ContactsException {
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

    public DirectoryMetadata[] goToPage(int number) throws ContactsException {
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
	 * 
	 * @see DirectoriesPageIteratorBusinessDelegate#freeResources()
	 */
	public void freeResources() {
        if (iterator != null) {
            try {
                iterator.remove();
            } catch (NoSuchObjectException e) {}
              catch (RemoveException e) {}
              catch (RemoteException e) {}
        }
        iterator = null;
	}
}
