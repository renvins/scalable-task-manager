package org.github.scalabletaskmanager.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling global exceptions in a Spring Boot application.
 * This class extends RuntimeException and includes an HTTP status code to provide
 * additional context for the exception.
 */
@Getter
public class GlobalException extends RuntimeException {

    private final HttpStatus httpStatus;

    public GlobalException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public GlobalException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
