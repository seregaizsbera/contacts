package su.sergey.contacts;

import su.sergey.contacts.exceptions.RuntimeGenericException;

public class RuntimeDelegateException extends RuntimeGenericException {

    public RuntimeDelegateException(String message, Throwable parent) {
         super(message, parent);
    }

    public RuntimeDelegateException(Throwable parent) {
         super(parent);
    }
}
