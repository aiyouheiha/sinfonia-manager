package com.heiha.sinfonia.manager.web.exception;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/11 15:23<br>
 * <b>Author:</b> heiha<br>
 */
public class ServiceInitException extends RuntimeException {
    public ServiceInitException() {
        super();
    }

    public ServiceInitException(String message) {
        super(message);
    }

    public ServiceInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceInitException(Throwable cause) {
        super(cause);
    }

    protected ServiceInitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
