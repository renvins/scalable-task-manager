package org.github.scalabletaskmanager.user.controller;

import org.github.scalabletaskmanager.user.gen.api.LoginAPI;
import org.github.scalabletaskmanager.user.gen.model.LoginUserDTO;
import org.github.scalabletaskmanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements LoginAPI {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> loginUser(LoginUserDTO userDTO) {
        String jwt = userService.login(userDTO);
        return ResponseEntity.ok(jwt);
    }
}
