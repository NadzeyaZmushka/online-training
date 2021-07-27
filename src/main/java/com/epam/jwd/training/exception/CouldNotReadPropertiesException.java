package com.epam.jwd.training.exception;

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
