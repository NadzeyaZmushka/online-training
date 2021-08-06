package com.epam.jwd.training.exception;

/**
 * Throws when problems are in services
 *
 * @author Nadzeya Zmushka
 */
public class ServiceException extends Exception {

    public ServiceException() {
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
