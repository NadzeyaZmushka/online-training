package com.epam.jwd.training.exception;

/**
 * Throws when it is impossible to initialize connection pool
 *
 * @author Nadzeya Zmushka
 */
public class CouldNotInitializeConnectionPoolException extends RuntimeException {

    public CouldNotInitializeConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

}
