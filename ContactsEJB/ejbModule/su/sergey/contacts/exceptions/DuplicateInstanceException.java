package su.sergey.contacts.exceptions;

import su.sergey.contacts.exceptions.ContactsException;

/**
 * DublicateInstanceException
 */
public class DuplicateInstanceException extends ContactsException {
    private int type;

    public DuplicateInstanceException(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
