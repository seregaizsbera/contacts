package su.sergey.contacts.person.businessdelegate.impl;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
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
	private String jndiName;

    private void initIterator() {
    	try {
    		Context context = new InitialContext();
    		Object object = context.lookup(jndiName);
    		PersonPageIteratorHome home = (PersonPageIteratorHome) PortableRemoteObject.narrow(object, PersonPageIteratorHome.class);
    		iterator = home.create(searchParameters, pageSize);
    	} catch (CreateException e) {
    		throw new RuntimeDelegateException(e);
    	} catch (NamingException e) {
    		throw new RuntimeDelegateException(e);
    	} catch (RemoteException e) {
    		throw new RuntimeDelegateException(e);
    	}
    }
    
	/**
	 * Constructor for DefaultPersonPageIteratorBusinessDelegate
	 */
	public DefaultPersonPageIteratorBusinessDelegate(String jndiName, PersonSearchParameters searchParameters, int pageSize) {
		if (searchParameters == null) {
			throw new NullPointerException("searchParamters");
		}
		this.searchParameters = searchParameters;
		this.pageSize = pageSize;
		this.iterator = null;
		this.jndiName = jndiName;
		initIterator();
	}

	/**
	 * @see PersonPageIteratorBusinessDelegate#prev()
	 */
	public Person2[] prev() {
        try {
            return iterator.prev();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.prev();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PersonPageIteratorBusinessDelegate#current()
	 */
	public Person2[] current() {
        try {
            return iterator.current();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.current();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PersonPageIteratorBusinessDelegate#next()
	 */
	public Person2[] next() {
        try {
            return iterator.next();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.next();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PersonPageIteratorBusinessDelegate#goTo(int)
	 */
	public Person2[] goTo(int page) {
        try {
            return iterator.goTo(page);
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.goTo(page);
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PageIteratorBusinessDelegate#hasNext()
	 */
	public boolean hasNext() {
        try {
            return iterator.hasNext();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.hasNext();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PageIteratorBusinessDelegate#hasPrev()
	 */
	public boolean hasPrev() {
        try {
            return iterator.hasPrev();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.hasPrev();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PageIteratorBusinessDelegate#getCurrentPage()
	 */
	public int getCurrentPage() {
        try {
            return iterator.getCurrentPage();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getCurrentPage();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PageIteratorBusinessDelegate#getPageSize()
	 */
	public int getPageSize() {
        try {
            return iterator.getPageSize();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getPageSize();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PageIteratorBusinessDelegate#getNumberOfPages()
	 */
	public int getNumberOfPages() {
        try {
            return iterator.getTotalPageCount();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getTotalPageCount();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
	}

	/**
	 * @see PageIteratorBusinessDelegate#freeResources()
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
