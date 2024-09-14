package org.github.scalabletaskmanager.task.exception;

public class TaskAlreadyExistsException extends RuntimeException {

    public TaskAlreadyExistsException(String message) {
        super(message);
    }

    public TaskAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
