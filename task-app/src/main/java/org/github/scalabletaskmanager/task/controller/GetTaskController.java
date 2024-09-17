package org.github.scalabletaskmanager.task.controller;

import org.github.scalabletaskmanager.common.service.JwtService;
import org.github.scalabletaskmanager.common.util.JwtUtil;
import org.github.scalabletaskmanager.task.exception.CreatorNotMatchException;
import org.github.scalabletaskmanager.task.gen.api.GetAPI;
import org.github.scalabletaskmanager.task.gen.model.TaskDTO;
import org.github.scalabletaskmanager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetTaskController implements GetAPI {

    private final TaskService taskService;
    private final JwtService jwtService;

    @Autowired
    public GetTaskController(TaskService taskService, JwtService jwtService) {
        this.taskService = taskService;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @Override
    public ResponseEntity<TaskDTO> getTask(Long id) {
        String jwt = JwtUtil.getJwtFromRequest();
        TaskDTO taskDTO = taskService.getTask(id);

        if (!jwtService.extractUsername(jwt).equals(taskDTO.getCreatorInfo().getUsername())) {
            throw new CreatorNotMatchException("No authorization for this task!", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(taskDTO);
    }
}
