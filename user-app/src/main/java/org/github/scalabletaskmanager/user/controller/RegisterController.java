package org.github.scalabletaskmanager.user.controller;

import org.github.scalabletaskmanager.user.gen.api.RegisterAPI;
import org.github.scalabletaskmanager.user.gen.model.RegisterUserDTO;
import org.github.scalabletaskmanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController implements RegisterAPI {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> registerUser(RegisterUserDTO registerUserDTO) {
        String jwt = userService.register(registerUserDTO);
        return ResponseEntity.ok(jwt);
    }
}
