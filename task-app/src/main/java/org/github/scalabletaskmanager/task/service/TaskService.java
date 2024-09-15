package org.github.scalabletaskmanager.task.service;

import org.github.scalabletaskmanager.task.gen.model.TaskDTO;

public interface TaskService {

    /**
     * Creates a new task based on the provided TaskDTO and JWT token.
     *
     * @param taskDTO the data transfer object containing task details such as title, description, status, and creator info
     * @param jwt the JSON Web Token used for authentication and authorization
     * @return the newly created TaskDTO containing the persisted task details
     */
    TaskDTO createTask(TaskDTO taskDTO, String jwt);
}
