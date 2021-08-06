package com.epam.jwd.training.exception;

/**
 * Throws when it is impossible to read property file
 *
 * @author Nadzeya Zmushka
 */
public class CouldNotReadPropertiesException extends RuntimeException {

    public CouldNotReadPropertiesException() {
    }

    public CouldNotReadPropertiesException(String message) {
        super(message);
    }

    public CouldNotReadPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouldNotReadPropertiesException(Throwable cause) {
        super(cause);
    }

}
