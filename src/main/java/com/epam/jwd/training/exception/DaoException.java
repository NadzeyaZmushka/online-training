package com.epam.jwd.training.exception;

/**
 * Throws when {@link java.sql.SQLException} are in dao
 *
 * @author Nadzeya Zmushka
 */
public class DaoException extends Exception {

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
