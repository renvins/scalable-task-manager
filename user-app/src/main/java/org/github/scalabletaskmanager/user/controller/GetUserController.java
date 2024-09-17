package org.github.scalabletaskmanager.user.controller;

import org.github.scalabletaskmanager.common.exception.UserNotFoundException;
import org.github.scalabletaskmanager.user.gen.api.GetAPI;
import org.github.scalabletaskmanager.user.gen.model.UserDTO;
import org.github.scalabletaskmanager.user.service.UserService;
import org.github.scalabletaskmanager.user.sql.UserEntity;
import org.github.scalabletaskmanager.user.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserController implements GetAPI {

    private final UserService userService;

    @Autowired
    public GetUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> getUser(String username) {
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User " + username + " not found!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
