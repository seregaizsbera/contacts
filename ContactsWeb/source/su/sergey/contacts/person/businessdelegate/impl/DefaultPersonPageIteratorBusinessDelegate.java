package su.sergey.contacts.person.businessdelegate.impl;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.JNDINamesForWeb;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.PersonPageIterator;
import su.sergey.contacts.person.PersonPageIteratorHome;
import su.sergey.contacts.person.businessdelegate.PersonPageIteratorBusinessDelegate;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.util.dao.DAOException;

public class DefaultPersonPageIteratorBusinessDelegate implements PersonPageIteratorBusinessDelegate {
	private PersonSearchParameters searchParameters;
	private int pageSize;
	private PersonPageIterator iterator;

    private void initIterator() {
    	try {
    		Context context = new InitialContext();
    		Object object = context.lookup(JNDINamesForWeb.PERSON_PAGE_ITERATOR_REFERENCE);
    		PersonPageIteratorHome home = (PersonPageIteratorHome) PortableRemoteObject.narrow(object, PersonPageIteratorHome.class);
    		iterator = home.create(searchParameters, pageSize);
    	} catch (CreateException e) {
    		e.printStackTrace();
    	} catch (NamingException e) {
    		e.printStackTrace();
    	} catch (RemoteException e) {
    		e.printStackTrace();
    	}
    }
    
	/**
	 * Constructor for DefaultPersonPageIteratorBusinessDelegate
	 */
	public DefaultPersonPageIteratorBusinessDelegate(PersonSearchParameters searchParameters, int pageSize) {
		if (searchParameters == null) {
			throw new NullPointerException("searchParamters");
		}
		this.searchParameters = searchParameters;
		this.pageSize = pageSize;
		this.iterator = null;
		initIterator();
	}

	/**
	 * @see PersonPageIteratorBusinessDelegate#prev()
	 */
	public Person2[] prev() throws ContactsException {
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

	/**
	 * @see PersonPageIteratorBusinessDelegate#current()
	 */
	public Person2[] current() throws ContactsException {
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

	/**
	 * @see PersonPageIteratorBusinessDelegate#next()
	 */
	public Person2[] next() throws ContactsException {
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

	/**
	 * @see PersonPageIteratorBusinessDelegate#goTo(int)
	 */
	public Person2[] goTo(int page) throws ContactsException {
        try {
            return iterator.goTo(page);
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.goTo(page);
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
	 * @see PageIteratorBusinessDelegate#hasNext()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#hasPrev()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#getCurrentPozition()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#getCurrentPage()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#getPageSize()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#getNumberOfPages()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#freeResources()
	 */
	public void freeResources() throws ContactsException {
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
