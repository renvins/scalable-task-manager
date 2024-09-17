package org.github.scalabletaskmanager.task.exception;

import org.github.scalabletaskmanager.common.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class TaskNotFoundException extends GlobalException {

    public TaskNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public TaskNotFoundException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }
}
