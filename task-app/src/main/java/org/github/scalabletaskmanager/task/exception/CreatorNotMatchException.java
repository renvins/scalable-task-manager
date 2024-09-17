package org.github.scalabletaskmanager.task.exception;

import org.github.scalabletaskmanager.common.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class CreatorNotMatchException extends GlobalException {

    public CreatorNotMatchException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public CreatorNotMatchException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }
}
