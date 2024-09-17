package org.github.scalabletaskmanager.task.exception;

import org.github.scalabletaskmanager.common.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class TaskAlreadyExistsException extends GlobalException {

    public TaskAlreadyExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public TaskAlreadyExistsException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }
}
