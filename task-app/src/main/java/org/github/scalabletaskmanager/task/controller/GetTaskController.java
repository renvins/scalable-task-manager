package org.github.scalabletaskmanager.task.controller;

import org.github.scalabletaskmanager.task.gen.api.GetAPI;
import org.github.scalabletaskmanager.task.gen.model.TaskDTO;
import org.github.scalabletaskmanager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetTaskController implements GetAPI {

    private final TaskService taskService;

    @Autowired
    public GetTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }
}
