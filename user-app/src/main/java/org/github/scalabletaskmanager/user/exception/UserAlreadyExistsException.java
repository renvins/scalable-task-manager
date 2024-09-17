package org.github.scalabletaskmanager.user.exception;

import org.github.scalabletaskmanager.common.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends GlobalException {

    public UserAlreadyExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public UserAlreadyExistsException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }
}
