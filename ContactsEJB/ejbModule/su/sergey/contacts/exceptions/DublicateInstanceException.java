package su.sergey.contacts.exceptions;

import su.sergey.contacts.exceptions.ContactsException;

/**
 * DublicateInstanceException
 */
public class DublicateInstanceException extends ContactsException {
    private int type;

    public DublicateInstanceException(int type) {
    	super();
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
