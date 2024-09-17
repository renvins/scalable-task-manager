package org.github.scalabletaskmanager.task.service.impl;

import org.github.scalabletaskmanager.common.service.JwtService;
import org.github.scalabletaskmanager.common.exception.UserNotFoundException;
import org.github.scalabletaskmanager.task.exception.TaskAlreadyExistsException;
import org.github.scalabletaskmanager.task.exception.TaskNotFoundException;
import org.github.scalabletaskmanager.task.gen.model.TaskDTO;
import org.github.scalabletaskmanager.task.gen.model.UserDTO;
import org.github.scalabletaskmanager.task.service.TaskService;
import org.github.scalabletaskmanager.task.service.UserClient;
import org.github.scalabletaskmanager.task.sql.TaskEntity;
import org.github.scalabletaskmanager.task.sql.TaskRepository;
import org.github.scalabletaskmanager.task.sql.TaskStatus;
import org.github.scalabletaskmanager.task.util.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final JwtService jwtService;
    private final UserClient userClient;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(JwtService jwtService, UserClient userClient, TaskRepository taskRepository) {
        this.jwtService = jwtService;
        this.userClient = userClient;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO, String jwt) {
        if (taskRepository.findByTitle(taskDTO.getTitle()) != null) {
            throw new TaskAlreadyExistsException("Task " + taskDTO.getTitle() + " already exists!");
        }
        String username = jwtService.extractUsername(jwt);
        UserDTO userDTO = userClient.getUserByUsername(username);

        if (userDTO == null) {
            throw new UserNotFoundException("User " + username + " not found!", HttpStatus.NOT_FOUND);
        }
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setDescription(taskDTO.getDescription());

        taskEntity.setCreatorId(userDTO.getId());
        taskEntity.setCreatorUsername(userDTO.getUsername());
        taskEntity.setCreatorFullName(userDTO.getFullName());

        taskEntity.setStatus(TaskStatus.TO_DO);
        taskRepository.save(taskEntity);

        return TaskMapper.toDTO(taskEntity, userDTO);
    }

    @Override
    public TaskDTO getTask(long id) {
        Optional<TaskEntity> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        return TaskMapper.toDTO(task.get());
    }

    @Override
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream().map(TaskMapper::toDTO).toList();
    }
}
