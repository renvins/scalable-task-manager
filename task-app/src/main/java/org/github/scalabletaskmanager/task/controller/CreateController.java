package org.github.scalabletaskmanager.task.controller;

import org.github.scalabletaskmanager.task.gen.api.CreateAPI;
import org.github.scalabletaskmanager.task.gen.model.TaskDTO;
import org.github.scalabletaskmanager.task.service.TaskService;
import org.github.scalabletaskmanager.task.sql.TaskEntity;
import org.github.scalabletaskmanager.task.util.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class CreateController implements CreateAPI {

    private final TaskService taskService;

    @Autowired
    public CreateController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskDTO taskDTO) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        String header = attributes.getRequest().getHeader("Authorization");
        String jwt = header.substring(7);

        TaskDTO finishedTaskDTO = taskService.createTask(taskDTO, jwt);
        return ResponseEntity.ok(finishedTaskDTO);
    }
}
