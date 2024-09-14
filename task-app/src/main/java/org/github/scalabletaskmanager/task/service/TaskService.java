package org.github.scalabletaskmanager.task.service;

import org.github.scalabletaskmanager.task.gen.model.TaskDTO;
import org.github.scalabletaskmanager.task.sql.TaskEntity;

public interface TaskService {

    /**
     * Creates a new task based on the provided TaskDTO object and JWT token.
     *
     * @param taskDTO the data transfer object containing task information
     * @param jwt the JSON Web Token used for authentication and authorization
     * @return the created TaskEntity
     */
    TaskEntity createTask(TaskDTO taskDTO, String jwt);
}
