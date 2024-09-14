package org.github.scalabletaskmanager.user.controller;

import org.github.scalabletaskmanager.common.exception.UserNotFoundException;
import org.github.scalabletaskmanager.user.gen.api.DefaultAPI;
import org.github.scalabletaskmanager.user.gen.model.UserDTO;
import org.github.scalabletaskmanager.user.service.UserService;
import org.github.scalabletaskmanager.user.sql.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserController implements DefaultAPI {

    private final UserService userService;

    @Autowired
    public GetUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> getUser(String username) {
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User " + username + " not found!");
        }

        UserDTO userDTO = new UserDTO(user.getId(), user.getFullName(), user.getUsername());
        return ResponseEntity.ok(userDTO);
    }
}
