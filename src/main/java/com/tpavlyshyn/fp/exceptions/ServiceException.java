package com.tpavlyshyn.fp.exceptions;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 6895802017494391490L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
