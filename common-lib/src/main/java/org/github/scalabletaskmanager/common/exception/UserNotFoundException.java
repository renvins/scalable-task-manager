package org.github.scalabletaskmanager.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GlobalException {

    public UserNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public UserNotFoundException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }
}
