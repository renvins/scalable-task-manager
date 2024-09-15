package org.github.scalabletaskmanager.task.util;

import org.github.scalabletaskmanager.task.gen.model.TaskDTO;
import org.github.scalabletaskmanager.task.gen.model.UserDTO;
import org.github.scalabletaskmanager.task.sql.TaskEntity;

public class TaskMapper {

    public static TaskDTO toDTO(TaskEntity taskEntity, UserDTO userDTO) {
        TaskDTO taskDTO = new TaskDTO(
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getStatus().toString());

        taskDTO.setCreatorInfo(userDTO);
        return taskDTO;
    }
}
